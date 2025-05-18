from sqlmodel import create_engine, Session

DATABASE_URL = "postgresql://usuario:senha@localhost:5432/biblioteca"

engine = create_engine(DATABASE_URL, echo=True)

def get_session():
    with Session(engine) as session:
        yield session
