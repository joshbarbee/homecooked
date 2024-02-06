from homecooked.router.middleware import Middleware
from homecooked.request import Request
from homecooked.response import Response
import os


class CSRFWare(Middleware):
    def __init__(self, app):
        super().__init__(app)

    def generate_token(self):
        return os.urandom(64).hex()

    async def preware(self, request): ...

    async def postware(self, request, response): ...
