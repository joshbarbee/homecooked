from homecooked import Request, Response, SubRouter

sr = SubRouter()

@sr.get('/test')
async def other_route(request : Request):
    return Response("Hello from imported route")

@sr.middleware('')
async def middleware(request : Request, next):
    print("Middleware from imported route")
    return await next(request)