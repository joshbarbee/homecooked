from homecooked.common.constants import HTTPMethods
from homecooked.request import Request
from homecooked.response import Response
from homecooked.router.constants import *
from homecooked.defaults.defaults import default_error_handlers
from homecooked.common.exceptions import (
    InvalidRoute,
    InvalidRouteParameter,
    InvalidPostware,
)

import re
from typing import Union, List

types = {"int": int, "str": str, "path": str}

type_regexes = {"int": r"(\\d+)", "str": r"(\\w+)", "path": r"(.+)"}

match_regex = f'{{([^\d\W]\w*):({"|".join(types.keys())})}}'


class RouteNode:
    def __init__(
        self, route, methods=None, handler=None, populate_request=False
    ) -> None:
        if (
            isinstance(methods, list)
            and len(methods) > 0
            and not isinstance(methods[0], HTTPMethods)
        ):
            methods = [
                HTTPMethods(method)
                for method in methods
                if not isinstance(method, HTTPMethods)
            ]

        self.route = route if route[0] == "/" else "/" + route
        self.paths = [p for p in route.split("/")[1:] if p]
        self.methods = methods if methods is not None else []
        self.handler = handler
        self.populate_request = populate_request

    async def call(self, request: Request):
        if self.populate_request:
            return await self.handler(request)
        return await self.handler()


class MiddlewareNode:
    def __init__(self, callable, route=None, request=False, response=False) -> None:
        self.route = route
        self.callable = callable
        self.request = request
        self.response = response

    async def call(self, request: Request, response: Response = None):
        if self.request and self.response:
            return await self.callable(request=request, response=response)
        elif self.request:
            return await self.callable(request=request)
        elif self.response:
            return await self.callable(response=response)
        return await self.callable()


class PathNode:
    def __init__(
        self, path, route=None, error_handlers=None, prewares=None, postwares=None
    ) -> None:
        self.children = PathChildren()
        self.path = path
        self.formatted_path = path
        self.error_handlers = error_handlers if error_handlers is not None else {}
        self.prewares = []
        self.postwares = []

        if prewares is not None:
            self.prewares.extend(prewares)
        if postwares is not None:
            self.postwares.extend(postwares)

        self.literal = "{" not in path and "}" not in path
        self.params = {}
        self.parent = None
        self.route = route

        if "{" in self.path and "}" not in self.path:
            raise InvalidRoute("Invalid partial path in route: " + self.path)

        if not self.literal:
            params = re.findall(match_regex, self.formatted_path)
            if len(params) > 0:
                for param, _type in params:
                    if not param.isidentifier():
                        raise InvalidRouteParameter(
                            "Invalid parameter name in route: " + self.path
                        )

                    self.params[param] = types[_type]
                    self.formatted_path = re.sub(
                        match_regex, type_regexes[_type], self.formatted_path, count=1
                    )

    def get_values(self, path):
        if self.literal:
            return {}

        params = self.params.copy()
        values = re.findall(self.formatted_path, path)

        if len(values) == 0:
            return {}

        for i, value in enumerate(
            values[0] if isinstance(values[0], tuple) else values
        ):
            param = list(params.keys())[i]
            params[param] = params[param](value)
        return params

    def get_error_handler(self, code):
        if code in self.error_handlers:
            return self.error_handlers[code]
        return self.parent.get_error_handler(code) if self.parent is not None else None

    def get_middlewares(self):
        prewares = self.prewares.copy()
        postwares = self.postwares.copy()
        if self.parent is not None:
            parent_prewares, parent_postwares = self.parent.get_middlewares()
            prewares = parent_prewares + prewares
            postwares = parent_postwares + postwares
        return (prewares, postwares)

    def add_prewares(self, prewares: List[MiddlewareNode]):
        self.prewares.extend(prewares)

    def add_postwares(self, postwares: List[MiddlewareNode]):
        self.postwares.extend(postwares)

    def __eq__(self, path):
        if self.literal:
            return self.path == path

        return re.fullmatch(self.formatted_path, path) is not None


class PathChildren(dict):
    def __init__(self) -> None:
        super().__init__()
        self.secondary_keys = {}

    def __setitem__(self, __key: str, __value: PathNode) -> None:
        if __value.literal:
            return super().__setitem__(__key, __value)
        self.secondary_keys[__key] = __value

    def __contains__(self, __key: str) -> bool:
        for key in self.keys():
            if key == __key:
                return True
        for key in self.secondary_keys.keys():
            if re.fullmatch(key, __key) is not None:
                return True
        return False

    def __getitem__(self, __key: str) -> object:
        for key in self.keys():
            if key == __key:
                return super().__getitem__(key)
        for key in self.secondary_keys.keys():
            if re.fullmatch(key, __key) is not None:
                return self.secondary_keys[key]

        raise KeyError(__key)


class RouteTree:
    def __init__(self) -> None:
        self.root = PathNode([RouteNode("/")])

    def add(self, route: RouteNode, prewares=None, postwares=None) -> None:
        current = self.root
        if route.route != "/":
            for path in route.paths:
                if path not in current.children:
                    current.children[path] = PathNode(path)
                    current.children[current.children[path].formatted_path] = (
                        current.children[path]
                    )
                    current.children[path].parent = current
                current = current.children[path]
        current.route = route
        current.add_prewares(prewares)
        current.add_postwares(postwares)

    def add_preware(self, middleware: MiddlewareNode) -> None:
        if not middleware.request:
            raise InvalidPostware("Preware must have and return a request parameter")

        if middleware.route == "/":
            self.root.prewares.append(middleware)
            return
        current = self.root
        for path in [p for p in middleware.route.split("/")[1:] if p]:
            if path not in current.children:
                current.children[path] = PathNode(path)
                current.children[current.children[path].formatted_path] = (
                    current.children[path]
                )
                current.children[path].parent = current
            current = current.children[path]
        current.prewares.append(middleware)

    def add_postware(self, middleware: MiddlewareNode) -> None:
        if not middleware.response:
            raise InvalidPostware("Postware must have and return a response parameter")

        if middleware.route == "/":
            self.root.postwares.append(middleware)
            return
        current = self.root
        for path in [p for p in middleware.route.split("/")[1:] if p]:
            if path not in current.children:
                current.children[path] = PathNode(path)
                current.children[current.children[path].formatted_path] = (
                    current.children[path]
                )
                current.children[path].parent = current
            current = current.children[path]
        current.postwares.append(middleware)

    def add_error_handler(self, code, route, callable, populate_request=False) -> None:
        if route == "/":
            self.root.error_handlers[code] = (callable, populate_request)
            return
        current = self.root
        for path in [p for p in route.split("/")[1:] if p]:
            if path not in current.children:
                current.children[path] = PathNode(path)
                current.children[current.children[path].formatted_path] = (
                    current.children[path]
                )
                current.children[path].parent = current
            current = current.children[path]
        current.error_handlers[code] = (callable, populate_request)

    def get(self, route: str, method: HTTPMethods) -> Union[RouteNode, None]:
        current = self.root
        if route == "/" and method in current.route.methods:
            return (ROUTE_FOUND, current.route, {}, *current.get_middlewares())

        params = current.get_values(route.split("/")[1:][0])

        for path in [p for p in route.split("/")[1:] if p]:
            if path not in current.children:
                return (ROUTE_NOT_FOUND, None, {}, *current.get_middlewares())
            current = current.children[path]
            params.update(current.get_values(path))

        if current.route is None:
            return (ROUTE_NOT_FOUND, None, {}, *current.get_middlewares())
        elif method in current.route.methods:
            return (ROUTE_FOUND, current.route, params, *current.get_middlewares())
        return (METHOD_NOT_ALLOWED, None, {}, *current.get_middlewares())

    def get_error_handler(self, code, route):
        current = self.root
        if route == "/":
            handler = current.error_handlers.get(code)
            return (
                handler
                if handler is not None
                else (default_error_handlers.get(code), False)
            )
        for path in [p for p in route.split("/")[1:] if p]:
            if path not in current.children:
                handler = current.get_error_handler(code)
                return (
                    handler
                    if handler is not None
                    else (default_error_handlers.get(code), False)
                )
            current = current.children[path]
        handler = current.get_error_handler(code)
        return (
            handler
            if handler is not None
            else (default_error_handlers.get(code), False)
        )
