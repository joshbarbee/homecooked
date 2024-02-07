Usage
=====

Installation
------------
First, install required packages via `Poetry <https://python-poetry.org/>`_. Once installed,
run the following command to install the package: ::


    poetry install


This will install the package and its dependencies in a virtual environment. As homecooked is 
ASGI-compliant, it can be run using any ASGI server. For development, 
`uvicorn <https://www.uvicorn.org/>`_ is recommended. 