import json
from typing import Dict, Any

class Response:
    def __init__(
        self, body : Any, status=200, headers : Dict[str, str] = None, mime_type : str = None, encoding : str = None
    ) -> None:
        self.status = status
        self.headers = headers if headers is not None else {}
        self.body = body
        self.mime_type = mime_type
        self.encoding = encoding

    @property
    def error(self):
        return self.status >= 400

    async def write(self, send, head=False) -> None:
        self.body = str(self.body)

        if self.headers.get("Content-Type") is None and self.mime_type is None:
            self.headers["Content-Type"] = "text/html"
        elif self.mime_type is not None:
            self.headers["Content-Type"] = self.mime_type
        self.headers["Content-Length"] = str(len(self.body))

        if self.encoding is not None:
            self.headers["Content-Encoding"] = self.encoding

        await send(
            {
                "type": "http.response.start",
                "status": self.status,
                "headers": [
                    [k.encode("utf-8"), v.encode("utf-8")]
                    for k, v in self.headers.items()
                ],
            }
        )

        if not head:
            await send(
                {
                    "type": "http.response.body",
                    "body": (
                        self.body.encode("utf-8")
                    ),
                }
            )
        else:
            await send(
                {
                    "type": "http.response.body",
                    "body": b"",
                }
            )

class JSONResponse(Response):
    def __init__(self, body : Dict[Any, Any], status=200, headers=None) -> None:
        super().__init__(body, status, headers)
        self.headers["Content-Type"] = "application/json"

    async def write(self, send, head=False) -> None:
        self.body = json.dumps(self.body)
        await super().write(send, head)

class TemplateResponse(Response):
    def __init__(self, template, context={}, status=200, headers=None) -> None:
        super().__init__(template, status, headers)
        self.context = context
        self.headers["Content-Type"] = "text/html"

    async def write(self, send, template_manager, head=False) -> None:
        template = template_manager.get(self.body)
        self.body = template.render(**self.context)
        await super().write(send, head)