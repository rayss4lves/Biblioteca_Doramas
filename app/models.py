from sqlalchemy.orm import registry, Mapped, mapped_column
from sqlalchemy import func
from datetime import datetime
# from sqlalchemy.orm import Session

table_registry = registry()


@table_registry.mapped_as_dataclass
class User:
    __tablename__ = 'users'
    
    id: Mapped[int] = mapped_column(init=False,primary_key=True)
    username: Mapped[str] = mapped_column(unique=True)
    password : Mapped[str]
    email : Mapped[str] = mapped_column(unique=True)
    created_at: Mapped[datetime] = mapped_column(init=False, server_default=func.now())
    

    # @classmethod
    # def cadastrar_usuario(session:Session):
    #     username = input('Nome do usuario: ')
    #     password = input('CPF do usuario: ')
    #     email = input('Senha do usuario: ')
        
    #     new_user = User(username=username, password=password, email=email)
    #     session.add(new_user)
    #     session.commit()
        
    #     print(f'Usuario {username} cadastrado!')
        
    #     return new_user