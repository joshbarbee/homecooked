from homecooked.router import Router
from homecooked.exceptions import HTTPException, ExceptionHandler, NotFound

class App:
    def __init__(self) -> None:
        self.router = Router()
        self.router.add_route("/", self.index)
        self.router.add_route("/about", self.about)
        self.router.add_route("/{abc:int}/{def:str}", self.contact)
        self.exception_handler = ExceptionHandler()

    async def index(self, request):
        return "Hello, World!"
    
    async def about(self, request):
        return "About us"
    
    async def contact(self, request, abc):
        return f"Contact us at {abc}"

    async def read_body(self, receive):
        body = b""
        more_body = True
        while more_body:
            message = await receive()
            body += message.get("body", b"")
            more_body = message.get("more_body", False)
        return body

    async def __call__(self, scope, receive, send):
        assert scope['type'] == 'http'

        body = await self.read_body(receive)

        route, params = self.router.get_route(scope['path'])

        if route is None:
            handler = self.exception_handler.handle_exception(NotFound())
            body = handler()
        else:
            try:
                body = await route.handler(params, body)
            except HTTPException as e:
                self.exception_handler.handle_exception(e)

        body = str(body).encode('utf-8')

        await send({
            'type': 'http.response.start',
            'status': 200,
            'headers': [
                (b'content-type', b'text/plain'),
                (b'content-length', str(len(body)).encode())
            ]
        })
        await send({
            'type': 'http.response.body',
            'body': body,
        })