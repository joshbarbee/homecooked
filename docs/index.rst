.. homecooked documentation master file, created by
   sphinx-quickstart on Tue Feb  6 20:55:59 2024.
   You can adapt this file completely to your liking, but it should at least
   contain the root `toctree` directive.

Welcome to homecooked's documentation!
======================================

**Homecooked** is a ASGI-compliant web server framework for Python 3.12. Read 
:ref:`tutorial-target` to get started with making your first application. See 
:ref:`api-target` for the full documentation of the framework.

Homecooked requires a ASGI-compliant server to run. Some compatible ASGI servers are:

   * `Uvicorn <https://www.uvicorn.org/>`_
   * `Daphne <http://github.com/django/daphne>`_
   * `Hypercorn <https://pgjones.gitlab.io/hypercorn/index.html>`_
   * `Granian <https://github.com/emmett-framework/granian>`_

Additionally, Homecooked requires `jinja2 <https://jinja.palletsprojects.com/>`_ for templating,
which can be installed via pip.

Homecooked is heavily inspired by `FastAPI <https://fastapi.tiangolo.com/>`_ and `Flask <https://flask.palletsprojects.com/>`_.

Contents
--------

.. toctree::

   install
   tutorial
   api

.. note::

   This project is under active development.