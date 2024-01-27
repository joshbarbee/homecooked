from enum import Enum

class HTTPMethods(Enum):
    GET = "GET"
    POST = "POST"
    PUT = "PUT"
    DELETE = "DELETE"
    PATCH = "PATCH"
    HEAD = "HEAD"

HTTP_VERSION: str = "HTTP/1.1"
