from fastapi.testclient import TestClient
from main import app
import pytest
from http import HTTPStatus

@pytest.fixture()
def client():
    return TestClient(app)