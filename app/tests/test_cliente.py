from conftest import client
from main import app
import pytest
from http import HTTPStatus

def test_read_root_deve_retornar_ok_e_ola(client):
    response = client.get('/')
    
    assert response.json() == {'message':'ola mundo'}
    
def test_creat_user(client):
    response = client.post(
        '/add', json={
            'username':'teste',
            'password':'password',
            'email':'teste@test.com',
        }
                 
                 )
    assert response.status_code == HTTPStatus.CREATE
    
    assert response.json()=={
            'username':'teste',
            'password':'password',
            'email':'teste@test.com',
            'id':'1'
            }
    