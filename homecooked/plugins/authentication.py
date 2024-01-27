from homecooked.router.middleware import Middleware
from homecooked.request import Request
from homecooked.response import Response
import hashlib
import os
import hmac
from datetime import datetime, timedelta

class SessionToken():
    def __init__(self, token : str, username : str, expiry : datetime = timedelta(days=1)):
        self.token = token
        self.username = username
        self.created = datetime.now()
        self.last_used = datetime.now()
        self.expiry = datetime.now() + expiry
        self._expired = False
    
    @property
    def expired(self):
        return self._expired or self.expiry < datetime.now()

    def set_expired(self):
        self._expired = True

class User():
    def __init__(self, username : str, password : str):
        self.username = username
        self.password = password
        self.sessions = []
        self.current_session = None

    def add_session(self, session : SessionToken):
        self.sessions.append(session)

        if self.current_session is None:
            self.current_session = session

    def is_valid_session(self, token : str):
        if self.current_session is None:
            return False
        
        if self.current_session.token != token:
            return False
        
        if self.current_session.expiry < datetime.now():
            return False
        
        return True
    
    def refresh_session(self, expiry : datetime = timedelta(days=1)):
        token = os.urandom(64).hex()
        self.current_session.last_used = datetime.now()
        session = SessionToken(token, self.username, expiry)
        self.add_session(session)
        return session

    def remove_session(self):
        if self.current_session is not None:
            self.current_session.set_expired()

        self.current_session = None

    
class InMemoryAuthWare(Middleware):
    def __init__(self, app, route = '/', login_route = '/login', logout_route = '/logout'):
        super().__init__(app, route)
        self.users = {}
        self.login_route = login_route
        self.logout_route = logout_route

    def hash_password(self, password : str):
        salt = os.urandom(16)
        key = hashlib.pbkdf2_hmac('sha256', password.encode('utf-8'), salt, 100000)
        return salt + key
    
    def verify_password(self, password : str, hashed_password : bytes, salt : bytes):
        return hmac.compare_digest(hashed_password, 
            hashlib.pbkdf2_hmac('sha256', password.encode('utf-8'), salt, 100000))
    
    def generate_session(self, username : str):
        expiry = self.app.config.session_expiry
        return SessionToken(token, username, expiry)
    
    async def preware_login(self, request : Request):
        data = await request.json()
        username = data['username']
        password = data['password']

        if username not in self.users:
            return Response('Unauthorized', status = 401)
        
        hashed_password = self.users[username]['password']
        salt = self.users[username]['salt']
        if not self.verify_password(password, hashed_password, salt):
            return Response('Unauthorized', status = 401)
        
        session = self.generate_session(username)
