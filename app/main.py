from fastapi import FastAPI, HTTPException
from schemas import Message, User, UserDb, userList, UserP


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


@app.get('/add', response_model=userList)
def read_users():
    return {'users':database}
  
@app.put('/add/{user_id}', response_model=UserP)
def update_user(user_id:int, user:User):
    if user_id < 1 or user_id > len(database):
        raise HTTPException(
            status_code = 404, detail = 'NOT FOUND'
        )
        
    user_with_id = UserDb(id=user_id, **user.model_dump())
    
    database[user_id - 1] = user_with_id
    return user_with_id

@app.delete('/add/{user_id}', response_model=Message)
def deletar_user(user_id:int):
    if user_id < 1 or user_id > len(database):
        raise HTTPException(
            status_code = 404, detail = 'NOT FOUND'
        )
    
    del database[user_id - 1] 
    return {'message': 'user deleted'}