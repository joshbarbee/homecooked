from typing import Callable

class HTTPException(Exception):
    code : int = -1
    message : str = None

    def __init__(self):
        super().__init__((self.code, self.message))

class BadRequest(HTTPException):
    code = 400
    message = "Bad Request"

class Unauthorized(HTTPException):
    code = 401
    message = "Unauthorized"

class Forbidden(HTTPException):
    code = 403
    message = "Forbidden"

class NotFound(HTTPException):
    code = 404
    message = "Not Found"

class MethodNotAllowed(HTTPException):
    code = 405
    message = "Method Not Allowed"

class NotAcceptable(HTTPException):
    code = 406
    message = "Not Acceptable"

class RequestTimeout(HTTPException):
    code = 408
    message = "Request Timeout"

class ServerError(HTTPException):
    code = 500
    message = "Internal Server Error"

class NotImplemented(HTTPException):
    code = 501
    message = "Not Implemented"

class ExceptionHandler():
    def default_handler(self, e : HTTPException) -> Callable:
        return lambda: (e.code, e.message)

    def __init__(self) -> None:
        self.handlers = {}

    def add_handler(self, code : int | HTTPException, handler : Callable) -> None:
        if isinstance(code, HTTPException):
            code = code.code

        self.handlers[code] = handler

    def handle_exception(self, e : HTTPException) -> Callable:
        return self.handlers.get(e.code, self.default_handler(e))