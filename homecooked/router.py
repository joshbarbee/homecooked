import re
from typing import List, Callable, Dict, Any, Tuple
from abc import ABC, abstractmethod

class Converter(ABC):
    regex = None

    @abstractmethod
    def convert(self, value : str) -> Any:
        pass

class IntConverter(Converter):
    regex = r"(\\d+)"

    def convert(self, value : str) -> int:
        return int(value)
    
class StrConverter(Converter):
    regex = r"(\\w+)"

    def convert(self, value : str) -> str:
        return value
    
class PathConverter(Converter):
    regex = r"(.+)"

    def convert(self, value : str) -> str:
        return value
        
class ConverterEngine():
    converters : Dict[str, Converter] = {
        "int": IntConverter(),
        "str": StrConverter(),
        "path": PathConverter()
    }

    path_match_regex = fr'{{([^\d\W]\w*):({"|".join(converters.keys())})}}'

    @classmethod
    def __call__(cls, *args: Any, **kwds: Any) -> Any:
        if not cls.path_match_regex:
            cls._set_path_regex()

    @classmethod 
    def _set_path_regex(cls) -> None:
        cls.path_match_regex = fr'{{([^\d\W]\w*):({"|".join(cls.converters.keys())})}}'

    @classmethod
    def add_converter(cls, name : str, converter : Converter) -> None:
        cls.converters[name] = converter
        cls._set_path_regex()

    @classmethod
    def format_path(cls, path : str) -> Tuple[str, Dict]:        
        params = re.findall(cls.path_match_regex, path)
        for _, type in params:
            path = re.sub(cls.path_match_regex, cls.converters[type].regex, path, 1)
        return (path, {param: type for param, type in params})

    @classmethod
    def convert(cls, name : str, value : str) -> Any:
        return cls.converters[name].convert(value)

class Route():
    def __init__(self, path : str, handler : Callable, dynamic = False):
        self.path : str = path
        self.handler : Callable = handler
        self.params : Dict[str, Any] = {}

        if dynamic:
            self.path, self.params = ConverterEngine.format_path(path)

    def match(self, path : str) -> bool:
        if not self.params:
            return path == self.path
        
        return re.fullmatch(self.path, path)
        
    def extract_params(self, path : str) -> Dict[str, Any]:
        if not self.params:
            return {}

        match = re.fullmatch(self.path, path)

        res = {}
        for (name, type), value in zip(self.params.items(), match.groups()):
            res[name] = ConverterEngine.convert(type, value)

        return res

class Router():
    def __init__(self):
        self.static_routes : List[Route] = []
        self.dynamic_routes : List[Route] = []

    def add_route(self, path : str, handler : callable) -> None:
        path = path.rstrip("/").lstrip("/")

        if "{" in path and "}" in path:
            self.add_dynamic_route(path, handler)
        else:
            self.add_static_route(path, handler)

    def add_static_route(self, path : str, handler : callable) -> None:
        self.static_routes.append(Route(path, handler))

    def add_dynamic_route(self, path : str, handler : callable) -> None:
        self.dynamic_routes.append(Route(path, handler, dynamic=True))

    def get_route(self, path : str) -> Tuple[Route, Dict[str, Any]]:
        path = path.rstrip("/").lstrip("/")

        for route in self.static_routes:
            if route.match(path):
                return route, route.extract_params(path)
            
        for route in self.dynamic_routes:
            if route.match(path):
                return route, route.extract_params(path)
            
        return None, None