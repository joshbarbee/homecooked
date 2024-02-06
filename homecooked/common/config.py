from dataclasses import dataclass
from datetime import timedelta


@dataclass
class Config:
    host: str = ""
    port: int = 8080
    template_dir: str = "templates"
    static_dir: str = "static"
    session_expiry: timedelta = timedelta(days=1)
