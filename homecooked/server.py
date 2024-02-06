from homecooked.router.router import RouteTree, MiddlewareNode, RouteNode
from homecooked.router import make_middlewares
from homecooked.router.static import StaticFileManager
from homecooked.templates import TemplateManager
from homecooked.common.constants import HTTPMethods
from homecooked.request import Request
from homecooked.response import Response, TemplateResponse
from homecooked.router.constants import ROUTE_FOUND, ROUTE_NOT_FOUND, METHOD_NOT_ALLOWED
from homecooked.common.config import Config
from homecooked.common.exceptions import InvalidPreware, InvalidPostware
from homecooked.router.middleware import Middleware
from inspect import getfullargspec


class App:
    def __init__(self, middleware=None):
        self.config = Config()
        self.static_file_manager = StaticFileManager(self.config)
        self.template_manager = TemplateManager(self.config)
        self.route_tree = RouteTree()

    async def read_body(self, receive):
        body = b""
        more_body = True
        while more_body:
            message = await receive()
            body += message.get("body", b"")
            more_body = message.get("more_body", False)
        return body

    async def __call__(self, scope, receive, send):
        if scope["type"] != "http":
            return

        if scope["method"] not in set(method.value for method in HTTPMethods):
            handler, _ = self.route_tree.get_error_handler(500, "/")
            response = await handler()
            await response.write(send)
            return

        request = Request(scope, await self.read_body(receive))
        status, handler, params, prewares, postwares = self.route_tree.get(
            request.path, request.method
        )
        request.params = params

        if status == ROUTE_NOT_FOUND and request.method in {
            HTTPMethods.GET,
            HTTPMethods.HEAD,
        }:
            res = self.static_file_manager.get(request.path)
            if res is not None:
                (mime_type, encoding), file = res
                response = Response(file, mime_type=mime_type, encoding=encoding)
                await response.write(send, head=request.method == HTTPMethods.HEAD)
                return

        if status == ROUTE_NOT_FOUND or status == METHOD_NOT_ALLOWED:
            handler, populate_resp = self.route_tree.get_error_handler(status, request.path)
            if populate_resp:
                for preware in prewares:
                    request = await preware.call(request)
                response = await handler(request)
                for postware in postwares:
                    response = await postware.call(request, response)
            else:
                for preware in prewares:
                    request = await preware.call(request)
                response = await handler()

                for postware in postwares:
                    response = await postware.call(request, response)
        else:
            for preware in prewares:
                request = await preware.call(request)

            response = await handler.call(request)
            if response.error:
                handler, populate_resp = self.route_tree.get_error_handler(
                    response.status, request.path
                )
                if handler is not None:
                    if populate_resp:
                        response = await handler(request)
                    else:
                        response = await handler()

            for postware in postwares:
                response = await postware.call(request, response)

        if isinstance(response, TemplateResponse):
            await response.write(
                send, self.template_manager, head=request.method == HTTPMethods.HEAD
            )
        else:
            await response.write(send, head=request.method == HTTPMethods.HEAD)

    def add_middleware(self, middleware: Middleware, route="/"):
        if not isinstance(middleware, Middleware):
            raise TypeError("Middleware must be of type Middleware")

        if not hasattr(middleware, "preware"):
            raise InvalidPreware("Middleware must have a preware method")

        if not hasattr(middleware, "postware"):
            raise InvalidPostware("Middleware must have a postware method")

        is_request_preware = "request" in getfullargspec(middleware.preware).args
        is_request_postware = "request" in getfullargspec(middleware.postware).args
        is_response_postware = "response" in getfullargspec(middleware.postware).args

        self.route_tree.add_preware(
            MiddlewareNode(middleware.preware, route, request=is_request_preware)
        )
        self.route_tree.add_postware(
            MiddlewareNode(
                middleware.postware,
                route,
                request=is_request_postware,
                response=is_response_postware,
            )
        )

    def get(self, route, prewares=[], postwares=[]):
        def decorator(func):
            self.route_tree.add(
                RouteNode(
                    route,
                    methods=[HTTPMethods.GET],
                    handler=func,
                    populate_request=len(getfullargspec(func).args) > 0,
                ),
                prewares=make_middlewares(prewares),
                postwares=make_middlewares(postwares),
            )
            return func

        return decorator

    def post(self, route, prewares=[], postwares=[]):
        def decorator(func):
            self.route_tree.add(
                RouteNode(
                    route,
                    methods=[HTTPMethods.POST],
                    handler=func,
                    populate_request=len(getfullargspec(func).args) > 0,
                ),
                prewares=make_middlewares(prewares),
                postwares=make_middlewares(postwares),
            )
            return func

        return decorator

    def put(self, route, prewares=[], postwares=[]):
        def decorator(func):
            self.route_tree.add(
                RouteNode(
                    route,
                    methods=[HTTPMethods.PUT],
                    handler=func,
                    populate_request=len(getfullargspec(func).args) > 0,
                ),
                prewares=make_middlewares(prewares),
                postwares=make_middlewares(postwares),
            )
            return func

        return decorator

    def delete(self, route, prewares=[], postwares=[]):
        def decorator(func):
            self.route_tree.add(
                RouteNode(
                    route,
                    methods=[HTTPMethods.DELETE],
                    handler=func,
                    populate_request=len(getfullargspec(func).args) > 0,
                ),
                prewares=make_middlewares(prewares),
                postwares=make_middlewares(postwares),
            )
            return func

        return decorator

    def patch(self, route, prewares=[], postwares=[]):
        def decorator(func):
            self.route_tree.add(
                RouteNode(
                    route,
                    methods=[HTTPMethods.PATCH],
                    handler=func,
                    populate_request=len(getfullargspec(func).args) > 0,
                ),
                prewares=make_middlewares(prewares),
                postwares=make_middlewares(postwares),
            )
            return func

        return decorator

    def route(self, route, prewares=[], postwares=[], methods=[]):
        def decorator(func):
            self.route_tree.add(
                RouteNode(
                    route,
                    methods=methods,
                    handler=func,
                    populate_request=len(getfullargspec(func).args) > 0,
                ),
                prewares=make_middlewares(prewares),
                postwares=make_middlewares(postwares),
            )
            return func

        return decorator

    def error(self, code, route="/"):
        def decorator(func):
            self.route_tree.add_error_handler(
                code,
                "/" + route if route[0] != "/" else route,
                func,
                populate_request=len(getfullargspec(func).args) > 0,
            )
            return func

        return decorator

    def preware(self, route):
        def decorator(func):
            is_request = "request" in getfullargspec(func).args
            self.route_tree.add_preware(MiddlewareNode(func, route, request=is_request))
            return func

        return decorator

    def postware(self, route, exclude=[]):
        def decorator(func):
            is_request = "request" in getfullargspec(func).args
            is_response = "response" in getfullargspec(func).args
            self.route_tree.add_postware(
                MiddlewareNode(
                    func,
                    route,
                    request=is_request,
                    response=is_response,
                    exclude=[exclude] if isinstance(exclude, str) else exclude,
                )
            )
            return func

        return decorator
