from pydantic import BaseModel, EmailStr

class User(BaseModel):
    username: str
    password: str 
    email: EmailStr

class UserDb(User):
    id:int
    
class UserP(BaseModel):
    id:int
    username: str
    email: EmailStr