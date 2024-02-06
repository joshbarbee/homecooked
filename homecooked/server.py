from homecooked.router import Router

class App:
    def __init__(self) -> None:
        self.router = Router()
        self.router.add_route("/", self.index)
        self.router.add_route("/about", self.about)
        self.router.add_route("/{abc:int}", self.contact)

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