from fastapi import APIRouter, HTTPException, Depends
from app.schemas import User, UserDB, UserP, Message
from http import HTTPStatus
from sqlmodel import Session, select
from app.shared.database import get_session

router = APIRouter(prefix='/usuarios', tags=['usuarios'])



@router.get('/fake', response_model=User)
def read_fake_user():
    return User(username="Rayssa", password="1234", email="rayssa@example.com")

@router.post('/add', response_model=UserP, status_code=HTTPStatus.OK)
def add_user(user: User, session: Session = Depends(get_session)):
    user_with_id = UserDB(**user.model_dump())
    session.add(user_with_id)
    session.commit()
    session.refresh(user_with_id)
    return user_with_id

@router.put('/add/{user_id}', response_model=UserP)
def update_user(user_id: int, user: User, session: Session = Depends(get_session)):
    db_user = session.get(UserDB, user_id)
    if not db_user:
        raise HTTPException(status_code=404, detail='User not found')
    
    for attr, value in user.model_dump().items():
        if value is not None:
            setattr(db_user, attr, value)
            
    # db_user.username = user.username
    # db_user.password = user.password
    # db_user.email = user.email
    
    session.commit
    session.refresh(db_user)
    return db_user

@router.delete('/add/{user_id}', response_model=Message)
def delete_user(user_id: int, session: Session = Depends(get_session)):
    db_user = session.get(UserDB, user_id)
    if not db_user:
        raise HTTPException(status_code=404, detail='User not found')
    session.delete(db_user)
    session.commit()
    return Message(message='User deleted successfully')

@router.get('/all', response_model=list[UserP])
def get_all_users(session: Session = Depends(get_session)):
    user = session.exec(select(UserDB)).all()
    if not user:
        raise HTTPException(status_code=404, detail='No users found')
    return user
