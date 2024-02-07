from homecooked.router import Router, PathTypes
from homecooked.exceptions.exceptions import (
    HTTPException, 
    ExceptionHandler, 
    NotFound, 
    MethodNotAllowed, 
    ServerError
)
from homecooked.request import Request
from homecooked.response import Response, TemplateResponse
from homecooked.constants import HTTPMethods
from homecooked.templates import TemplateManager

class App:
    def __init__(self) -> None:
        self.router = Router()
        self.exception_handler = ExceptionHandler()
        self.template_manager = TemplateManager()

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

        path_type, path, params = self.router.get_path(request.path, request.method)

        match path_type:
            case PathTypes.STATIC | PathTypes.DYNAMIC:
                request.params = params
                call_stack = self.router.get_middlewares(request.path)
                call_stack.append(path)
                try:
                    response = await call_stack(request)
                except HTTPException as e:
                    handler = self.exception_handler.get_handler(e)
                    response = await handler(request)
            case PathTypes.FILE:
                (mime_type, encoding), body = path, params
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
            await response.write(send, self.template_manager, request.method == "HEAD")
        else:  
            await response.write(send, request.method == "HEAD")

    def add_path(self, path, handler, method):
        self.router.add_path(path, handler, method)

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