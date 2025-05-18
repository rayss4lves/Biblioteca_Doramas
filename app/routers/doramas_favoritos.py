from fastapi import APIRouter, Depends, HTTPException
from app import crud, schemas
from sqlalchemy.orm import Session
from app.shared.database import get_db

router = APIRouter(prefix="/favoritos", tags=["Dorama Favoritos"])

@router.post("/", response_model=schemas.Dorama)
def adicionar_dorama(dorama: schemas.DoramaCreate, db: Session = Depends(get_db)):
    return crud.adicionar_dorama(db=db, dorama=dorama, tipo='favorito')

@router.get("/", response_model=list[schemas.Dorama])
def listar_doramas(db: Session = Depends(get_db)):
    return crud.listar_doramas(db=db, tipo='favorito')
