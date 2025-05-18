from fastapi import FastAPI
from app.shared.database import Base, engine
from app.routers.usuarios import router as user_router  # Corrigido esse import

Base.metadata.drop_all(bind=engine)
Base.metadata.create_all(bind=engine)

app = FastAPI()

app.include_router(user_router)


