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

Breaking this code down, we first import the `App`, `Request`, and `Response` classes from
`homecooked`. Then a new instance of `App` is created. Via the `app.get` decorator, the 
`index` function is registered as a handler for the `"/""` route. When a user makes a request 
to `localhost:8000/`, the `index` function is called and returns a `Response` object with body
`"Hello, world!"`.

All handlers must be async functions that take a single `Request` object as an argument and
return a `Response` object. 

Routing
-------

Homecooked supports routing of both static and dynamic paths. Static paths are defined as
simple strings, while dynamic paths use regular expressions to match patterns. The syntax
for variables in dynamic paths is `{variable_name:type}`. ::

    @app.get("/admin")    
    async def admin(request):
        return Response("Admin Page")

    @app.get("/user/{user_id:int}")
    async def user(request, user_id):
        return Response(f"User ID: {user_id}")

Dynamic paths will always match after static paths. For example, if the URL `/user/1` is a
static route, and `/user/{user_id:int}` is a dynamic route, if the path is `/user/1`, the
static route will always match first. Dynamic paths also will match after files located in
the static directory (typically `static/`).

By default, types supported for dynamic paths are `int`, `float`, `str`, and `path`. 
Support for custom types is enabled via the :ref:`converter-engine`.