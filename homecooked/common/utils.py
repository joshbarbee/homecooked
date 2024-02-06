from typing import Dict, List


def parse_headers(headers: List[bytes]) -> Dict[str, str]:
    return {k.decode("utf-8"): v.decode("utf-8") for k, v in headers}


def parse_query(query: bytes) -> Dict[str, str]:
    if "&" not in query.decode("utf-8"):
        return {}
    res = {}
    for pair in query.decode("utf-8").split("&"):
        k, v = pair.split("=")
        if k not in res:
            res[k] = v
        else:
            if isinstance(res[k], list):
                res[k].append(v)
            else:
                res[k] = [res[k], v]
    return res
