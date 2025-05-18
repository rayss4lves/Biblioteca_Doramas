from pydantic import EmailStr
from sqlmodel import SQLModel, Field
from typing import Optional

class Message(SQLModel):
    message:str

class User(SQLModel):
    username: str
    password: str 
    email: EmailStr
    
class UserP(SQLModel):
    id:int
    username: str
    email: EmailStr

#modelo banco de dados   
class UserDB(SQLModel, table=True):
    __tablename__ = 'users'
    id: Optional[int] = Field(default=None, primary_key=True)
    username: str
    password: str 
    email: EmailStr