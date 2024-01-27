# if we type this we get circular imports

class Middleware():
    def __init__(self, app, route = '/'):
        self.app = app
        self.app.add_middleware(self)

    async def preware(self, request):
        return request
    
    async def postware(self, request, response):
        return response