import re
from typing import List, Callable, Dict, Any, Tuple
from abc import ABC, abstractmethod
import os
from mimetypes import guess_type
from enum import Enum

from homecooked.constants import HTTPMethods
from homecooked.request import Request
from homecooked.response import Response

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
    
class FloatConverter(Converter):
    regex = r"(\\d+\.\\d+)"

    def convert(self, value : str) -> float:
        return float(value)
        
class ConverterEngine():
    converters : Dict[str, Converter] = {
        "int": IntConverter(),
        "str": StrConverter(),
        "path": PathConverter(),
        "float": FloatConverter()
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

class Path():
    def __init__(self, path : str, handler : Callable, method : HTTPMethods, dynamic = False):
        self.path : str = path
        self.handler : Callable = handler
        self.method : HTTPMethods = method
        self.params : Dict[str, Any] = {}
        self.dynamic = dynamic

        if self.dynamic:
            self.path, self.params = ConverterEngine.format_path(path)

    def match(self, path : str) -> bool:
        if not self.dynamic:
            return path == self.path
        
        return re.fullmatch(self.path, path)
        
    def extract_params(self, path : str) -> Dict[str, Any]:
        if not self.dynamic:
            return {}

        match = re.fullmatch(self.path, path)

        res = {}
        for (name, type), value in zip(self.params.items(), match.groups()):
            res[name] = ConverterEngine.convert(type, value)

        return res
    
class Middleware():
    def __init__(self, handler : Callable, path = '', dynamic = False) -> None:
        self.handler = handler
        self.path = path
        self.dynamic = dynamic

        if self.dynamic:
            self.path, _ = ConverterEngine.format_path(path)

    def match(self, path : str) -> bool:
        if self.path == "":
            return True
                
        return re.match(self.path, path) is not None

class MiddlewareStack(list):
    def __init__(self, *args, **kwds) -> None:
        super().__init__(*args, **kwds)

    async def __call__(self, request : Request) -> Response:
        if len(self) == 1: # we are calling the actual function
            return await self.pop(0).handler(request)

        return await self.pop(0).handler(request, self)

class PathTypes(Enum):
    STATIC = "STATIC"
    DYNAMIC = "DYNAMIC"
    FILE = "FILE"
    NOPATH = "NOPATH"
    NOMETHOD = "NOMETHOD"

class Router():
    def __init__(self, static_folder = 'static'):
        self.static_paths : List[Path] = []
        self.dynamic_paths : List[Path] = []
        self.static_files : List[Path] = []
        self.static_folder = static_folder
        self.middlewares : MiddlewareStack[Middleware] = MiddlewareStack()

    def add_middleware(self, handler : Callable, path = '') -> None:
        path = path.rstrip("/").lstrip("/")

        self.middlewares.append(Middleware(handler, path, dynamic="{" in path and "}" in path))

    def get_middlewares(self, path : str) -> List[Middleware]:
        path = path.rstrip("/").lstrip("/")

        return MiddlewareStack(middleware for middleware in self.middlewares if middleware.match(path))

    def is_static_file(self, path : str) -> bool:
        return os.path.isfile(os.path.join(os.getcwd(), self.static_folder, path))

    def get_static_file(self, path : str) -> bool:
        path = os.path.join(os.getcwd(), self.static_folder, path)
        return guess_type(path), open(path, "rb").read()

    def add_path(self, path : str, handler : callable, method : HTTPMethods) -> None:
        path = path.rstrip("/").lstrip("/")

        if "{" in path and "}" in path:
            self.add_dynamic_path(path, handler, method)
        else:
            self.add_static_path(path, handler, method)

    def add_static_path(self, path : str, handler : callable, method : HTTPMethods) -> None:
        self.static_paths.append(Path(path, handler, method))

    def add_dynamic_path(self, path : str, handler : callable, method : HTTPMethods) -> None:
        self.dynamic_paths.append(Path(path, handler, method, dynamic=True))

    def get_path(self, path : str, method : HTTPMethods) -> Tuple[PathTypes, Path, Dict[str, Any]]:
        path = path.rstrip("/").lstrip("/")
        invalid_method = False

        for p in self.static_paths:
            res = p.match(path)
            if res and (p.method == method or (p.method == HTTPMethods.GET and method == HTTPMethods.HEAD)):
                return PathTypes.STATIC, p, p.extract_params(path)
            elif res:
                invalid_method = True
            
        if method in {HTTPMethods.GET, HTTPMethods.HEAD} and self.is_static_file(path):
            return PathTypes.FILE, *self.get_static_file(path)
            
        for p in self.dynamic_paths:
            res = p.match(path)
            if res and (p.method == method or (p.method == HTTPMethods.GET and method == HTTPMethods.HEAD)):
                return PathTypes.DYNAMIC, p, p.extract_params(path)
            elif res:
                invalid_method = True
            
        return PathTypes.NOMETHOD if invalid_method else PathTypes.NOPATH, None, None
        