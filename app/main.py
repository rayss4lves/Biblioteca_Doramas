from fastapi import FastAPI
from app.routers import usuarios
from sqlmodel import SQLModel
from app.shared.database import engine
# from contextlib import asynccontextmanager

app = FastAPI()

SQLModel.metadata.create_all(bind=engine)




app.include_router(usuarios.router)


