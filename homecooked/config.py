from dataclasses import dataclass

@dataclass
class HomecookedConfig:
    static_dir : str = "static"
    template_dir : str = "templates"
    char_encoding : str = "utf-8"
    debug : bool = False
    port : int = 8000

