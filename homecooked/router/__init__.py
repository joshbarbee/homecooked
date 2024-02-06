from homecooked.router.router import RouteTree, RouteNode, MiddlewareNode
from inspect import getfullargspec

def make_middlewares(middlewares):
    res = []

    if not isinstance(middlewares, list):
        middlewares = [middlewares]

    for middleware in middlewares:
        is_request = "request" in getfullargspec(middleware).args
        is_response = "response" in getfullargspec(middleware).args
        res.append(MiddlewareNode(middleware, request=is_request, response=is_response))
    return res
