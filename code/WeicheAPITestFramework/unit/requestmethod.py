__author__ = 'liliang'

import requests
from unit.api_client import ApiClient

def test01(url):
    response = requests.get(url)
    return response.content


