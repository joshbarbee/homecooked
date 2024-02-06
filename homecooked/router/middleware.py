# if we type this we get circular imports


class Middleware:
    def __init__(self, app):
        self.app = app
        self.app.add_middleware(self)

    async def preware(self, request):
        return request

    async def postware(self, request, response):
        return response

    def add_middleware(self, app):
        self.app.add_middleware(app)
