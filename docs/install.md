# Installation

First, install required packages via [Poetry](<https://python-poetry.org/>). Run the 
following commands to install the package and activate the virtual environment: 

    poetry install
    poetry shell

This will install the package and its dependencies in a virtual environment. As homecooked is 
ASGI-compliant, it can be run using any ASGI server. For development, 
[Uvicorn](https://www.uvicorn.org/>) is recommended, which can be installed via
        
    pip install uvicorn

Once all dependencies as well as an ASGI server are installed, it is now possible to start
using homecooked. See [Tutorial](#tutorial-target) for a quick introduction to the package and [API Reference](#api-target) for a detailed description of the Homecooked API.