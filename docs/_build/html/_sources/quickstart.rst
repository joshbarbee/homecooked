Tutorial
==========

Creating First Application
--------------------------

To get started running the server, we must first create a simple application. Create a new file
`app.py`. In this file, we will define a simple ASGI application using homecooked. ::

    from homecooked import App, Request, Response

    app = App()

    @app.get("/")
    async def index(request):
        return Response("Hello, world!")

Once the file is created, we can then run it via Uvicorn. ::

    python run uvicorn app:app --reload

This will start the server by default on `localhost:8000`. You can then navigate to
the URL in your browser to see the response.