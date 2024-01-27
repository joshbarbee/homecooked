from homecooked.router.router import RouteTree, MiddlewareNode
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
    def __init__(self, middleware = None):
        self.config = Config()
        self.static_file_manager = StaticFileManager(self.config)
        self.template_manager = TemplateManager(self.config)

    async def read_body(self, receive):
        body = b''
        more_body = True
        while more_body:
            message = await receive()
            body += message.get('body', b'')
            more_body = message.get('more_body', False)
        return body

    async def __call__(self, scope, receive, send):
        if scope['type'] != 'http':
            return

        if scope['method'] not in set(method.value for method in HTTPMethods):
            handler, _ = RouteTree.get_error_handler(500, '/')
            response = await handler()
            await response.write(send)
            return
        
        request = Request(scope, await self.read_body(receive))
        status, handler, params, prewares, postwares  = RouteTree.get(request.path, request.method)
        request.params = params

        if status == ROUTE_NOT_FOUND and request.method in {HTTPMethods.GET, HTTPMethods.HEAD}:
            res = self.static_file_manager.get(request.path)
            if res is not None:
                (mime_type, encoding), file = res
                response = Response(file, mime_type=mime_type, encoding=encoding)
                await response.write(send, head=request.method == HTTPMethods.HEAD)
                return
            
        if status == ROUTE_NOT_FOUND or status == METHOD_NOT_ALLOWED:
            handler, populate_resp = RouteTree.get_error_handler(status, request.path)
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
            for postware in postwares:
                response = await postware.call(request, response)

        if isinstance(response, TemplateResponse):
            await response.write(send, self.template_manager, head = request.method == HTTPMethods.HEAD)
        else:
            await response.write(send, head = request.method == HTTPMethods.HEAD)

    def add_middleware(self, middleware : Middleware, route = '/'):
        if not isinstance(middleware, Middleware):
            raise TypeError('Middleware must be of type Middleware')

        if not hasattr(middleware, 'preware'):
            raise InvalidPreware('Middleware must have a preware method')
        
        if not hasattr(middleware, 'postware'):
            raise InvalidPostware('Middleware must have a postware method')
        
        is_request_preware = 'request' in getfullargspec(middleware.preware).args
        is_request_postware = 'request' in getfullargspec(middleware.postware).args
        is_response_postware = 'response' in getfullargspec(middleware.postware).args

        RouteTree.add_preware(MiddlewareNode(middleware.preware, route, request=is_request_preware))
        RouteTree.add_postware(MiddlewareNode(middleware.postware, route, request=is_request_postware, response=is_response_postware))
