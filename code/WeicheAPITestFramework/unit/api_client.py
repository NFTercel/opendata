#!/usr/bin/python
# -*- coding: utf-8 -*-

from datetime import datetime
from hashlib import md5
from json import loads,JSONDecoder,dumps

from requests import Request, Session


class ApiError(StandardError):
    def __init__(self, ret_error):
        self.responce = ret_error
        StandardError.__init__(self, ret_error['error'])

    def __str__(self):
        return '***%s %s*** %s' % (
            self.responce['code'],
            self.responce['error'],
            self.responce.get('request', ''))

class ApiClient(object):
    def __init__(self, host, key, secret):
        self.host = host
        print self.host
        self.key = key
        self.secret = secret
        self.cookie = None

    def get(self, url):
        return self.execute('get', url)

    def post(self, url, data):
        return self.execute('post', url, data)

    def delete(self, url):
        return self.execute('delete', url)

    def put(self, url, data):
        return self.execute('put', url, data)

    def execute(self, method, url, data=None):
        session = Session()
        print 'URL: ',self.host + url
        req = Request(method, self.host + url, data=data).prepare()
        headers = self.gen_headers(
            method.upper(), url, req.headers.get('Content-Length', 0))

        if self.cookie:
            headers['Cookie'] = self.cookie
        if isinstance(data, basestring):
            headers['Content-Type'] = 'application/json'
        req.headers.update(headers)
        resp = session.send(req)
        if 'set-cookie' in resp.headers:
            self.cookie = resp.headers['set-cookie']

        try:
            ret = loads(resp.content)
            p = dumps(ret,ensure_ascii=False,indent=1)
            print p

        except ValueError as e:
            return resp.content
        #if 'code' in ret and 'error' in ret:
        #    raise ApiError(ret)
        #return ret
        return p


    def gen_headers(self, method, api, content_length):
        headers = {}
        now = datetime.utcnow().strftime('%a, %d %b %Y %H:%M:%S GMT')
        signature_list = [
            method,
            api,
            now,
            str(content_length),
            md5(self.secret).hexdigest()
        ]
        signature = md5('&'.join(signature_list)).hexdigest()
        headers['Date'] = now
        headers['Authorization'] = '%s:%s' % (self.key, signature)
        return headers
