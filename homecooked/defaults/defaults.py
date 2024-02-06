from homecooked.response import Response


async def default_error_handler():
    return Response("<h1>500 - Internal Server Error</h1>", 500)


async def default_not_found_handler():
    return Response("<h1>404 - Not Found</h1>", 404)


async def default_method_not_allowed_handler():
    return Response("<h1>405 - Method Not Allowed</h1>", 405)


default_error_handlers = {
    500: default_error_handler,
    404: default_not_found_handler,
    405: default_method_not_allowed_handler,
}
