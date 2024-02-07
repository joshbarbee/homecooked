(tutorial-target)=
# Tutorial

## Creating First Application

To get started running the server, we must first create a simple application. Create a new file
`app.py`. In this file, we will define a simple ASGI application using homecooked.

    from homecooked import App, Request, Response

    app = App()

    @app.get("/")
    async def index(request):
        return Response("Hello, world!")


Once the file is created, we can then run it via Uvicorn.

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

## Routing

Homecooked supports routing of both static and dynamic paths. Static paths are defined as
simple strings, while dynamic paths use regular expressions to match patterns. The syntax
for variables in dynamic paths is `{variable_name:type}`.

    @app.get("/admin")    
    async def admin(request):
        return Response("Admin Page")

    @app.get("/user/{user_id:int}")
    async def user(request):
        return Response(f"User ID: {request.params.get('user_id')}")

Dynamic paths will always match after static paths. For example, if the URL `/user/1` is a
static route, and `/user/{user_id:int}` is a dynamic route, if the path is `/user/1`, the
static route will always match first. Dynamic paths also will match after files located in
the static directory (typically `static/`).

By default, types supported for dynamic paths are `int`, `float`, `str`, and `path`. 
Support for custom types is enabled via the [Converter-Engine](#converter-target)

## Parsing Request Information

When a handler is invoked, we can access information from the current request via the passed `Request` object. 
- Path parameters are stored as a dictionary between a path variable and value, accessed via `request.params`. For example, `request.params.get('user_id')`
- Query parameters are accessed via `request.query`. Like path parameters, query parameters are stored as a dictionary, but keys and values are both strings. If multiple parameters are passed with the same key, the value becomes an array of all values with the same key.
- Request body can be accessed via `request.body`. In this form, the request body is raw text. Request bodies can be parsed via JSON through invoking the `.json` function on a request object (e.g. `data = await request.json()`)


## HTTP Methods

When connecting a handler to a path, you must specifiy the HTTP method for the specific
handler. A particular path can have multiple handlers, but a handler can only have one
method. Supported methods in Homecooked are:
- GET
- POST
- PUT
- DELETE
- PATCH
- HEAD
- OPTIONS

As such, to support multiple methods, multiple handlers should be used, such as the following:

    @app.get("/user/{user_id:int}")  
    async def admin(request):
        return Response("GET endpoint for a particular user")

    @app.post("/user/{user_id:int}")
    async def user(request, user_id):
        return Response(f"User ID: {request.params.get('user_id')}")

Homecooked automatically supports the `HEAD` HTTP method for all resources that `GET` is enabled for.

## Static Files

Static files are typically located in the `./static` folder and serve content such as images, CSS, or JavaScript. The default path of the Homecooked static folder is `./static`. 

When a user attempts to access a file within the static folder, first, the router will determine if a static handler was created for the path. If no static handler is found, then Homecooked will look
in the configured static folder for a file matching the provided path, and will serve that file. 

Thus, if a user tries to access a file such as `favicon.ico`, the file should be stored at `./static/favicon.ico`

## Templating

Templating of HTML files is done via [Jinja2](https://jinja.palletsprojects.com/). Templates are stored by default in the `./templates` folder. To return a rendered template from a handler, first import TemplateResponse from `homecooked`. Then, in a handler, return a new `TemplateResponse`, with the name of the template and a dictionary of the arguments for Jinja2 to parse.

First, create the `./templates` folder if it does not exist. Then, copy and paste the below HTML into `./templates/index.html`

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
    </head>
    <html lang="en">
    <body>
        <p> Hello {{ name }} </p>
    </body>

In your application file (`main.py / app.py / whatever`), create the following route:

    @app.get('/')
    async def home(request : Request):
        return TemplateResponse('index.html', {"name": "world"})

The handler will capture all GET requests to the index page of our site. In the handler, we return a TemplateResponse, providing the name of the template, as well as the dictionary of arguments to use when templating. 

More detailed information about templating can be found at the [Jinja2 docs](https://jinja.palletsprojects.com/).

Escaping is enabled by default and must be manually deactivated. 

## Middleware

To access a request before the handler processes a request or the response after the handler is done, you can use middleware. There are two ways to implement middleware:
- Function-Based Approach

        @app.middleware('/json')
        async def json_middleware(request : Request, next):
            print("json middleware, only applied on /json route")
            res = await next(request)
            print("after the request")
            return res

    Function-based middleware should have two parameters: `request` and `next`, a callable to invoke the next function in the middleware stack. In middleware, `next` must be called manually, with the `request` object being the sole argument passed, to ensure the path handler is called. You can access and modify `request` before the call to `next` and `response` after the call. Finally, the response is returned by the middleware.

- Class-Based Approach
        from homecooked import Middleware, App

        app = App()

        class MyMiddleware(Middleware):
            async def __call__(self, request : Request, next) -> Response:
                print("Middleware in a class!")
                res = await next(request)
                print("After the request!")
                return res

        MyMiddleware(app)
    
    Class-based middleware should inherit the base class from `homecooked.Middleware`. We override
    the `__call__` function of `homecooked.Middleware` in our own middleware. The parameters are the same as in function-based middleware and we make a similar call to `await next(request)`. Unlike function-based middleware, class-based middlewares are attached to the app via passing
    a reference to `app` when creating the middleware.

Both types of middleware support an optional path parameter. Middleware with a specified path will only run when a user-supplied path begins with the middleware path. 

Middleware is ran in the order the middleware functions are defined / middleware classes are connected to the app. For example:
    app = App()

    class MyMiddleware(Middleware):
        ...

    @app.middleware()
    def middlewareA(request : Request, next):
        ...

    MyMiddleWare(app)

    @app.middleware('/test')
    def middlewareB(request : Request, next):
        ...

The first middleware function to be connected to the index route is `middlewareA`, since we do not invoke the `MyMiddleware` class before `middlewareA` is defined. Then, we register `MyMiddleware` with the app and lastly, `middlewareB` is registed to the `/test` path. When a user makes a request to our index page, `middlewareA` is executed first, then `MyMiddleware`. When a request is made for a path beginning in `/test`, first `middlewareA` is executed, then `MyMiddleware`, and lastly `middlewareB`. 

Middleware supports dynamic paths. Variables set via dynamic paths are accessible via the `request` object, like in normal requests. 

