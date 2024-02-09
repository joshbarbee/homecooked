from homecooked.exceptions.exceptions import ModelKeyError, ModelValueError

class BaseModel:
    _fields = {}

    def __init__(self, data, strict = True) -> None:
        if not self._fields:
            self._get_fields()

        for key, value in data.items():
            if key in self._fields:
                if strict:
                    if not isinstance(value, self._fields[key]):
                        raise ModelValueError(f"Value of {key} is not of type {self._fields[key]}")
                    setattr(self, key, value)
                else:
                    setattr(self, key, self._fields[key](value))
            else:
                raise ModelKeyError(f"Key {key} is not a valid field")
    
    @classmethod
    def _get_fields(cls):
        cls._fields = cls.__annotations__