import json # Para poder trabajar con objetos JSON

from elasticsearch import Elasticsearch
from elasticsearch import helpers

def main():
    # Password para el usuario 'lectura' asignada por nosotros
    #
    READONLY_PASSWORD = "abretesesamo"

    # Creamos el cliente y lo conectamos a nuestro servidor
    #
    global es

    es = Elasticsearch(
        "https://localhost:9200",
        ca_certs="./http_ca.crt",
        basic_auth=("lectura", READONLY_PASSWORD)
    )

    # Lanzamos el scaneo
    results = helpers.scan(es,
        index="tweets-20090624-20090626-en_es-10percent",
        query={
            "query": {
                "query_string": {
                    "query": "text:\"michael jackson\" AND lang:en"
                }
            }
        }
    )

    f=open("scan-dump.txt", "wb")

    # Iteramos sobre los resultados, no es preciso preocuparse de las
    # conexiones consecutivas que hay que hacer con el servidor ES
    for hit in results:
        text = hit["_source"]["text"]

        # Para visualizar mejor los tuits se sustituyen los saltos de línea
        # por espacios en blanco *y* se añade un salto de línea tras cada tuit
        text = text.replace("\n"," ")+"\n"
        f.write(text.encode("UTF-8"))

    f.close()

if __name__ == '__main__':
    main()