from fastapi import APIRouter, HTTPException
from app.schemas import User, UserDb, UserP, Message
from http import HTTPStatus

router = APIRouter(prefix='/usuarios', tags=['usuarios'])

database = []

@router.get('/fake', response_model=User)
def read_fake_user():
    return User(username="Rayssa", password="1234", email="rayssa@example.com")

@router.post('/add', response_model=UserP, status_code=HTTPStatus.OK)
def add_user(user: User):
    user_with_id = UserDb(id=len(database)+1, **user.model_dump())
    database.append(user_with_id)
    return UserP(id=user_with_id.id, username=user_with_id.username, email=user_with_id.email)

@router.put('/add/{user_id}', response_model=UserP)
def update_user(user_id: int, user: User):
    if user_id < 1 or user_id > len(database):
        raise HTTPException(status_code=404, detail='User not found')
    
    user_with_id = UserDb(id=user_id, **user.model_dump())
    database[user_id - 1] = user_with_id
    return user_with_id

@router.delete('/add/{user_id}', response_model=Message)
def delete_user(user_id: int):
    if user_id < 1 or user_id > len(database):
        raise HTTPException(status_code=404, detail='User not found')
    
    del database[user_id - 1]
    return {'message': 'User deleted'}

@router.get('/all', response_model=list[UserDb])
def get_all_users():
    return database
