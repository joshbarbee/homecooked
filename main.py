from homecooked import App, Response, Request, exceptions
import time
from other import sr

app = App()

app.add_subrouter('/other', sr)

@app.route('/')
async def home(bacon : Request):
    return Response("Hello from home route")

@app.route('/{name:str}')
async def variable(name : str):
    if len(name) > 10:
        raise exceptions.BadRequest

    return Response(f"Hello {name} from json route")

@app.middleware('/')
async def middleware(request : Request, next):
    start = time.time()
    response = await next(request)
    print(f"Time taken: {time.time() - start}")
    return response