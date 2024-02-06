import os
from mimetypes import guess_type
from homecooked.common.config import Config


class StaticFileManager:
    def __init__(self, config: Config) -> None:
        self.path = os.path.join(os.getcwd(), config.static_dir)

    def get(self, path):
        path = path[1:] if path[0] == "/" else path
        file = os.path.join(self.path, path)
        if os.path.isfile(file):
            return (guess_type(file), open(file, "rb").read())
        else:
            return None
