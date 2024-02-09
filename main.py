from homecooked.basemodel import BaseModel
from homecooked import App, Request, Response, TemplateResponse
import time

class TestData(BaseModel):
    data : str = 10
    age : int
    status: bool = False

    def __init__(self, data) -> None:
        super().__init__(data, strict = False)

data = {
    "data": "Hello",
    "age": 20,
    "status": True,
}

app = App()

@app.route('/')
async def home(bacon : Request):
    return TemplateResponse("index.html", {})

@app.post('/data')
async def data_route(data : TestData):
    return Response(f"Hello from data route {data.data}")

@app.middleware('/')
async def middleware(request : Request, next):
    start = time.time()
    response = await next(request)
    print(f"Time taken: {time.time() - start}")
    return response