from homecooked import App, router, Response, JSONResponse, TemplateResponse, Middleware

app = App()

class MyWare(Middleware):
    async def preware(self, request):
        print('preware')
        return request

    async def postware(self, request, response):
        print('postware')
        return response

MyWare(app)

@router.get('/')
async def test(request):
    print(request.params)
    return TemplateResponse('index.html', {'name': 'Mike'})


@router.route('/test', methods=['POST'])
async def test(request):
    print(await request.json())
    return JSONResponse({'status': 'ok'})

