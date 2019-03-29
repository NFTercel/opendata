#-*- coding: utf-8 -*-

from django.http import HttpResponse
from django.shortcuts import render
from django.shortcuts import render_to_response
import unit.requestmethod
from  unit.paramformat import formatdata

from unit.swichmethod import switch
from  unit.api_client import ApiClient

import json


#test sever
host = 'https://api.wcar.net.cn/test'
#第三方加油
key = '5vRjMzRAQq1A5P1j'
secret = 'YvK8vRwqK3QEtkH6YTRlBkSncIuO1uPR'


#serever online
#host = 'https://api.wcar.net.cn/'


api_client = ApiClient(host, key, secret)


def  index(request):
    #api address
    apiadr = request.GET.get('q','')
    # post get delete put 关键字
    method = request.GET.get('test001','')
    #post参数
    param = request.GET.get('txtParams','')

    temp={}

    for case in switch(method):
        if case('GET'):
            temp=api_client.get(apiadr)
            break
        if case('POST'):
            data={}
            data = formatdata(param)
            #temp=data
            temp=api_client.post(apiadr,json.dumps(data))
            break
        if case('DELETE'):
            temp="delete暂未对外开放！！！"
            break

        if case('PUT'):
            data = {
                'order_status':'canceled',
            }
            temp=api_client.put(apiadr,json.dumps(data))
            break

        if case():
            temp='请选择请求的方式！！！'
            break

    return render_to_response("index.html",{'results':apiadr,"select01":method,'areatext':param,'temp':temp,})


#def test01(url):
#    apiadr = 'GET'
#    for case in switch(apiadr):
#        if case('GET'):
#            temp=api_client.get(url)
#            #print temp
#            return temp
#
#def test02(url,data):
#    print api_client.post(url,json.dumps(data))


#oil_name": "92#",
#  "fee": 1000,
#  "device_num": "6",
#  "order_id": "61700117338417",
#  "receipt": "微车",
#  "original_fee": 1200,
#  "phone": "17701310761",
#  "weiche_price": 500,
#  "oil_station_id": 1,
#  "license_plate_num": "测1234588989",
#  "order_status": "paid",
#  "weiche_fee": 750,
#  "uuid": "17907029c2264b40954a5f6d2af8e87f"
#
#test001={'oil_name': '92#', 'fee': '2000', 'uuid': '17907029c2264b40954a5f6d2af8e87f', 'partner_weiche_fee': '64000', 'receipt': '\xe5\xbe\xae\xe8\xbd\xa6', 'original_fee': '70000', 'phone': '12345678901', 'oil_station_id': '1', 'license_plate_num': '\xe4\xba\xac123456', 'device_num': '1'}
##test01("/open_oil/cities")

#t02="oil_station_id= 1 &oil_name=92# & device_num=11 & license_plate_num=测123456 & phone= 12345678901 & fee= 100 & original_fee= 100 & partner_weiche_fee=100 & receipt= 微车"
#test02("/open_oil/orders",formatdata(t02))





























