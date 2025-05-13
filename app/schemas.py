from pydantic import BaseModel, EmailStr

class User(BaseModel):
    username: str
    cpf: str
    password: str 
    email: EmailStr
