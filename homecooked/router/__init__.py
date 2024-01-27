from homecooked.router.router import RouteTree, RouteNode, MiddlewareNode
from homecooked.common.constants import HTTPMethods
from inspect import getfullargspec

def get(route, prewares = [], postwares = []):
    def decorator(func):
        RouteTree.add(
            RouteNode(
                route, 
                methods = [HTTPMethods.GET], 
                handler=func, 
                populate_request=len(getfullargspec(func).args) > 0
            ), 
            prewares=make_middlewares(prewares), 
            postwares=make_middlewares(postwares)
        )
        return func
    return decorator

def post(route, prewares = [], postwares = []):
    def decorator(func):
        RouteTree.add(
            RouteNode(
                route, 
                methods = [HTTPMethods.POST], 
                handler=func, 
                populate_request=len(getfullargspec(func).args) > 0
            ), 
            prewares=make_middlewares(prewares),
            postwares=make_middlewares(postwares)
        )
        return func
    return decorator

def put(route, prewares = [], postwares = []):
    def decorator(func):
        RouteTree.add(
            RouteNode(
                route, 
                methods = [HTTPMethods.PUT], 
                handler=func, 
                populate_request=len(getfullargspec(func).args) > 0
            ), 
            prewares=make_middlewares(prewares),
            postwares=make_middlewares(postwares)
        )
        return func
    return decorator

def delete(route, prewares = [], postwares = []):
    def decorator(func):
        RouteTree.add(
            RouteNode(
                route, 
                methods = [HTTPMethods.DELETE], 
                handler=func, 
                populate_request=len(getfullargspec(func).args) > 0
            ), 
            prewares=make_middlewares(prewares),
            postwares=make_middlewares(postwares))
        return func
    return decorator

def patch(route, prewares = [], postwares = []):
    def decorator(func):
        RouteTree.add(
            RouteNode(
                route, 
                methods = [HTTPMethods.PATCH], 
                handler=func, 
                populate_request=len(getfullargspec(func).args) > 0
            ), 
            prewares=make_middlewares(prewares), 
            postwares=make_middlewares(postwares)
        )
        return func
    return decorator

def route(route, prewares = [], postwares = [], methods = []):
    def decorator(func):
        RouteTree.add(
            RouteNode(
                route, 
                methods = methods, 
                handler=func, 
                populate_request=len(getfullargspec(func).args) > 0
            ), 
            prewares=make_middlewares(prewares),
            postwares=make_middlewares(postwares)
        )
        return func
    return decorator

def error(code, route = '/'):
    def decorator(func):
        RouteTree.add_error_handler(code, '/' + route  if route[0] != '/' else route, func, populate_request=len(getfullargspec(func).args) > 0)
        return func
    return decorator

def prewares(route):
    def decorator(func):
        is_request = 'request' in getfullargspec(func).args
        RouteTree.add_preware(MiddlewareNode(func, route, request=is_request))
        return func
    return decorator

def postwares(route, exclude = []):
    def decorator(func):
        is_request = 'request' in getfullargspec(func).args
        is_response = 'response' in getfullargspec(func).args
        RouteTree.add_postware(MiddlewareNode(func, route, request=is_request, response=is_response, exclude = [exclude] if isinstance(exclude, str) else exclude))
        return func
    return decorator

def make_middlewares(middlewares):
    res = []

    if not isinstance(middlewares, list):
        middlewares = [middlewares]

    for middleware in middlewares:
        is_request = 'request' in getfullargspec(middleware).args
        is_response = 'response' in getfullargspec(middleware).args
        res.append(MiddlewareNode(middleware, request=is_request, response=is_response))
    return res