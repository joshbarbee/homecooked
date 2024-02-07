from jinja2 import Environment, FileSystemLoader, select_autoescape, TemplateNotFound
import os

class TemplateManager:
    def __init__(self, template_dir = 'templates') -> None:
        path = os.path.join(os.getcwd(), template_dir)
        loader = FileSystemLoader(path)
        self.env = Environment(
            loader=loader,
            autoescape=select_autoescape(default_for_string=True, default=True),
        )

    def get(self, template_name):
        return self.env.get_template(template_name)