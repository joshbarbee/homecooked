from jinja2 import Environment, FileSystemLoader, select_autoescape, TemplateNotFound
from homecooked.common.config import Config
import os


class TemplateManager:
    def __init__(self, config: Config) -> None:
        path = os.path.join(os.getcwd(), config.template_dir)
        loader = FileSystemLoader(path)
        self.env = Environment(
            loader=loader,
            autoescape=select_autoescape(default_for_string=True, default=True),
        )

    def get(self, template_name):
        return self.env.get_template(template_name)
