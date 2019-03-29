#-*- coding: utf-8 -*-

__author__ = 'liliang'

import re
import uuid

data = []
map01 = {}

#使用的数据不能是重复的
#t01 = '  oil_station_id=2  & oil_name = 92# &oil_station_id1=2&oil_name1 = 92#  '

#t02="oil_station_id= 1 &oil_name=92# & device_num=1 & uuid=demo_order_uuid & license_plate_num=京123456 & phone= 12345678901 & fee= 65000 & original_fee= 70000 & partner_weiche_fee=64000 & receipt= 微车"


def formatdata(param):

    #获得字典
    temp = re.split(r'[&]\s*', param)
    for i in temp:
        print "i: ",i
        i01 = i.strip()
        dict01 = re.split(r'[=]\s*',i01)
        if (dict01[0]=="oil_station_id") or (dict01[0]=="fee") or (dict01[0]=="original_fee") or (dict01[0]=="partner_weiche_fee"):
            map01[dict01[0].strip()]=int(dict01[1].strip())
        else:
            map01[dict01[0].strip()]=dict01[1].strip()


    #print map01
    map01['uuid'] = uuid.uuid4().hex
    #print map01

    return map01




#formatdata(t02)
