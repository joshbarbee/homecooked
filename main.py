from homecooked import App, Response, Request, JSONResponse, exceptions, TemplateResponse
import time
from homecooked.middleware import Middleware

app = App()    
Middleware(app, '/json')

@app.get('/')
async def home(request : Request):
    return TemplateResponse('index.html', {"name": "world"})

@app.get('/json/test')
async def json(request : Request):
    return JSONResponse({"hello": "world"})

@app.middleware()
async def middleware(request : Request, next):
    start = time.time()
    response = await next(request)
    print(f"Request took {time.time() - start} seconds")
    return response

@app.middleware('/json')
async def json_middleware(request : Request, next):
    print("json middleware, only applied on /json route")
    return await next(request)

