from typing import Dict, List
from homecooked.config import HomecookedConfig

enc = HomecookedConfig.char_encoding

def parse_headers(headers: List[bytes]) -> Dict[str, str]:
    return {k.decode(enc): v.decode(enc) for k, v in headers}

def parse_query(query: bytes) -> Dict[str, str]:
    if "&" not in query.decode(enc):
        return {}
    res = {}
    for pair in query.decode(enc).split("&"):
        k, v = pair.split("=")
        if k not in res:
            res[k] = v
        else:
            if isinstance(res[k], list):
                res[k].append(v)
            else:
                res[k] = [res[k], v]
    return res