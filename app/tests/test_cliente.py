from fastapi.testclient import TestClient
from app.main import app
from http import HTTPStatus

client = TestClient(app)

def test_read_fake_user():
    response = client.get('/usuarios/fake')
    assert response.status_code == HTTPStatus.OK
    assert response.json() == {
        'username': 'Rayssa',
        'password': '1234',
        'email': 'rayssa@example.com'
    }

def test_create_user():
    response = client.post(
        '/usuarios/add',
        json={
            'username': 'teste',
            'password': 'password',
            'email': 'teste@test.com',
        }
    )
    assert response.status_code == HTTPStatus.OK
    assert response.json() == {
        'id': 1,
        'username': 'teste',
        'email': 'teste@test.com'
    }
