from fastapi import FastAPI
from schemas import User, UserDb, UserP

from http import HTTPStatus

app = FastAPI()

database = []

@app.get('/1', response_model=User, status_code=HTTPStatus.OK)
def create_user():
    return User(username="Rayssa", password="1234", email="rayssa@example.com")  # Retorna um modelo válido


@app.post('/add', response_model=UserP, status_code=HTTPStatus.OK)
def add(user:User):
   
    user_with_id = UserDb(id=len(database)+1, **user.model_dump())  # Corrigido model_dump()
    database.append(user_with_id)
    return UserP(id=user_with_id.id, username=user_with_id.username, email=user_with_id.email)

