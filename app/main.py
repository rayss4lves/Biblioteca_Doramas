from fastapi import FastAPI

from app.routers.usuarios import router as user_router  # Corrigido esse import

app = FastAPI()

database = []

app.include_router(user_router)


