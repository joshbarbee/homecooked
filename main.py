from homecooked import App, Response

app = App()

@app.get("/")
async def index():
    return Response("Hello World!")

@app.get("/test")
async def test(request):
    if 'a' not in request.query:
        return Response("a not in query", 500)
    
    return Response("a in query")

@app.error(500)
async def error():
    return Response("Server Error!!", 500)