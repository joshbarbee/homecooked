from dataclasses import dataclass
from homecooked.common.constants import HTTPMethods
from homecooked.common.utils import parse_headers, parse_query
from typing import Dict, Any
import json

@dataclass
class Request():
    server_ip: str
    server_port: int
    client_ip: str
    client_port: int
    method: HTTPMethods
    path: str
    headers: Dict[str, str]
    query: Dict[str, str]
    params: Dict[str, Any]
    body: str

    def __init__(self, scope, body : bytes) -> None:
        self.server_ip = scope['server'][0]
        self.server_port = scope['server'][1]
        self.client_ip = scope['client'][0]
        self.client_port = scope['client'][1]
        self.method = HTTPMethods(scope['method'])
        self.path = scope['path']
        self.headers = parse_headers(scope['headers'])
        self.query = parse_query(scope['query_string'])
        self.params = {}
        self.body = body.decode('utf-8')
            
    async def json(self):
        return json.loads(self.body)