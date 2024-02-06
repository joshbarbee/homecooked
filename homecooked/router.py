import re

types = {"int": int, "str": str, "path": str}

type_regexes = {"int": r"(\\d+)", "str": r"(\\w+)", "path": r"(.+)"}

match_regex = fr'{{([^\d\W]\w*):({"|".join(types.keys())})}}'

class Route():
    def __init__(self, path, handler, dynamic = False):
        self.path = path
        self.handler = handler
        self.params = {}

        if dynamic:
            self._convert_to_regex(path)

    def _convert_to_regex(self, path):
        params = re.findall(match_regex, path)
        for param, type in params:
            self.params[param] = type
            path = re.sub(match_regex, type_regexes[type], path, 1)

        self.path = path

    def match(self, path):
        if not self.params:
            return path == self.path

        return re.fullmatch(self.path, path)
        
    def extract_params(self, path):
        if not self.params:
            return {}

        match = re.fullmatch(self.path, path)
        return {name: types[type](value) for (name, type), value in zip(self.params.items(), match.groups())}

class Router():
    def __init__(self):
        self.static_routes = []
        self.dynamic_routes = []

    def add_route(self, path, handler):
        if "{" in path and "}" in path:
            self.add_dynamic_route(path, handler)
        else:
            self.add_static_route(path, handler)

    def add_static_route(self, path, handler):
        self.static_routes.append(Route(path, handler))

    def add_dynamic_route(self, path, handler):
        self.dynamic_routes.append(Route(path, handler, dynamic=True))