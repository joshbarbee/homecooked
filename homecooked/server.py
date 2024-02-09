from homecooked.router import Router, PathTypes, SubRouter
from homecooked.exceptions.httpexceptions import (
    HTTPException, 
    ExceptionHandler, 
    NotFound, 
    MethodNotAllowed, 
    ServerError,
    BadRequest
)
from homecooked.exceptions.exceptions import (
    ModelKeyError, 
    ModelValueError
)
from homecooked.request import Request
from homecooked.response import Response, TemplateResponse
from homecooked.constants import HTTPMethods
from homecooked.templates import TemplateManager
from homecooked.config import HomecookedConfig
from homecooked.basemodel import BaseModel

class App:
    def __init__(self) -> None:
        self.models : dict[str, BaseModel]= {}
        self.config = HomecookedConfig()
        self.router = Router(self.config.static_dir)
        self.exception_handler = ExceptionHandler()
        self.template_manager = TemplateManager(self.config.template_dir)

    async def read_body(self, receive):
        body = b""
        more_body = True
        while more_body:
            message = await receive()
            body += message.get("body", b"")
            more_body = message.get("more_body", False)
        return body

    async def __call__(self, scope, receive, send):
        assert scope['type'] == 'http'

        request = Request(scope, await self.read_body(receive))

        path_type, path, request.params = self.router.get_path(request.path, request.method)
        call_stack = self.router.get_middlewares(request.path)

        match path_type:
            case PathTypes.STATIC | PathTypes.DYNAMIC:
                if request.method in {HTTPMethods.POST, HTTPMethods.PATCH, HTTPMethods.PUT} and path.model is not None:
                    try:
                        request.model = path.model(await request.json())
                        call_stack.append(path)
                    except (ModelKeyError, ModelValueError) as e:
                        call_stack = self.exception_handler.get_handler(BadRequest())
                else:
                    call_stack.append(path)
                try:
                    response = await call_stack(request)
                except HTTPException as e:
                    handler = self.exception_handler.get_handler(e)
                    response = await handler(request)
            case PathTypes.FILE:
                (mime_type, encoding), body = path, request.params
                response = Response(body, mime_type=mime_type, encoding=encoding)
            case PathTypes.NOPATH:
                handler = self.exception_handler.get_handler(NotFound())
                response = await handler(request)
            case PathTypes.NOMETHOD:
                handler = self.exception_handler.get_handler(MethodNotAllowed())
                response = await handler(request)
            case _:
                handler = self.exception_handler.get_handler(ServerError())
                response = await handler(request)

        if isinstance(response, TemplateResponse):
            await response.write(send, self.template_manager, request.method == HTTPMethods.HEAD)
        else:  
            await response.write(send, request.method == HTTPMethods.HEAD)

    def add_path(self, path, handler, method):
        model = None
        if method in {HTTPMethods.POST, HTTPMethods.PATCH, HTTPMethods.PUT}:
            for value in handler.__annotations__.values():
                if issubclass(value, BaseModel):
                    model = value
        self.router.add_path(path, handler, method, model)

    def add_subrouter(self, path : str, subrouter : SubRouter):
        path = path.rstrip("/").lstrip("/")
        for sr_path, handler, method in subrouter.paths:
            joined_path = f"{path}/{sr_path.rstrip("/").lstrip("/")}"
            self.add_path(joined_path, handler, method)
        
        for sr_path, middleware in subrouter.middlewares:
            joined_path = f"{path}/{sr_path.rstrip("/").lstrip("/")}"
            self.router.add_middleware(middleware, sr_path)

    def route(self, path, methods = None):
        if methods is None:
            methods = [HTTPMethods.GET]
        elif not isinstance(methods, list):
            methods = [methods]

        def decorator(handler):
            for method in methods:
                if isinstance(method, str):
                    method = HTTPMethods(method.upper())
                self.add_path(path, handler, method)
            return handler
        return decorator

    def get(self, path):
        def decorator(handler):
            self.add_path(path, handler, HTTPMethods.GET)
            return handler
        return decorator
    
    def post(self, path):
        def decorator(handler):
            self.add_path(path, handler, HTTPMethods.POST)
            return handler
        return decorator
    
    def put(self, path):
        def decorator(handler):
            self.add_path(path, handler, HTTPMethods.PUT)
            return handler
        return decorator
    
    def delete(self, path):
        def decorator(handler):
            self.add_path(path, handler, HTTPMethods.DELETE)
            return handler
        return decorator
    
    def patch(self, path):
        def decorator(handler):
            self.add_path(path, handler, HTTPMethods.PATCH)
            return handler
        return decorator
    
    def head(self, path):
        def decorator(handler):
            self.add_path(path, handler, HTTPMethods.HEAD)
            return handler
        return decorator
    
    def middleware(self, path = ''):
        def decorator(handler):
            self.router.add_middleware(handler, path)
            return handler
        return decorator

    