import urllib
from urllib.request import urlopen
from urllib.parse import urlencode
import urllib
from bs4 import BeautifulSoup as BS
import os
import sys
import glob
import json
import lxml

searchLink = "https://spb.leroymerlin.ru/search/?q="
materials_1 = ["плитка", "шпатлевка выравнивающая", "силикон герметик", "грунтовки под плитку",
               "Крестики для плитки", "клей для плитки", "затирка для швов"]  # плитка
materials_2 = ["грунтовка под обои", "клей под обои", "обои"]  # обои
materials_3 = ["раствор для стяжки пола", "грунтовка для пола"]  # стяжка пола
materials_4 = ["оцинкованный профиль монтажный", "гипсокартон", "саморезы гипсокартон-металл",
               "шумоизоляция", "деревянные бруски"]  # гипсокартонные перегородки
materials = [materials_1, materials_2, materials_3, materials_4]
goods = []

for materialList in materials:
    for material in materialList:
        # html = urlopen(searchLink+material)
        # soupPages = BS(html, features="lxml")
        # totalPages = soupPages.select_one("body > div.page-wrapper > div.top-page-wrapper > div.content.pinned-footer > " +
        #                                  "div > div > div > div > div > div.search-content-tabs > div > " +
        #                                  "div.filtered-products.clearfix.search-section.products > div > " +
        #                                  "div.products-right-column.pull-right > div > div.catalog-plp.page-1 > " +
        #                                  "div.service-panel-wrapper > div > div:nth-child(4) > div > div > " +
        #                                  "div.items-wrapper > div:nth-child(4) > a").text
        for page in range(1, 2):
            parameters = {"page": str(page)}
            url = '{}&{}'.format(searchLink + urllib.request.quote(material), urlencode(parameters))
            print(url)
            htmlpage = urlopen(url)
            soupMaterialPage = BS(htmlpage, features="lxml")
            products = soupMaterialPage.findAll("div", {"class": "ui-product-card"})
            print(products.__len__())
            for product in products:
                product_info = {
                     "url": product['data-product-url'],
                     "price": product['data-product-price'],
                   # "material": product["data-product-material"],
                   # "color": product['data-product-color'],
                     "weight": product['data-product-weight'],
                     "name": product['data-product-name']
                }
                print(product_info['name'])
                goods.append(product_info)

with open('data.json', 'w', encoding='utf-8') as f:
    json.dump(goods, f, ensure_ascii=False)
