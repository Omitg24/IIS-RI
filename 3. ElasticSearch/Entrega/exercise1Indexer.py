from datetime import datetime

from elasticsearch import Elasticsearch, helpers

import json


def main():
    # Password para el usuario 'elastic' generada por Elasticsearch
    #
    ELASTIC_PASSWORD = "63UgPgko03NlYYUnbLpi"

    # Creamos el cliente y lo conectamos a nuestro servidor
    #
    global es

    es = Elasticsearch(
        "https://localhost:9200",
        ca_certs="./http_ca.crt",
        basic_auth=("elastic", ELASTIC_PASSWORD)
    )

    inicio = datetime.now()

    # Argumentos del indexer
    #
    argumentos = {
        "settings": {
            "analysis": {
                "filter": {
                    "bi_tri_gramas": {
                        "type": "shingle",
                        "min_shingle_size": 2,
                        "max_shingle_size": 3
                    }
                },
                "analyzer": {
                    "analizador_personalizado": {
                        "tokenizer": "standard",
                        "filter": ["lowercase", "bi_tri_gramas"]
                    }
                }
            }
        },
        "mappings": {
            "properties": {
                "created_at": {
                    "type": "date",
                    "format": "EEE MMM dd HH:mm:ss Z yyyy"
                },
                "text": {
                    "type": "text",
                    "analyzer": "analizador_personalizado",
                    "fielddata": "true"
                }
            }
        }
    }

    es.indices.create(index="tweets-20090624-20090626-en_es-10percent-exercise1", ignore=400, body=argumentos)

    # Ahora se indexan los documentos.
    # Leemos el fichero en grandes bloques
    #
    global contador
    contador = 0

    tamano = 40 * 1024 * 1024  # Para leer 40MB, tamaño estimado de manera experimental
    fh = open("tweets-20090624-20090626-en_es-10percent.ndjson", 'rt')
    lineas = fh.readlines(tamano)
    while lineas:
        procesarLineas(lineas)
        lineas = fh.readlines(tamano)
    fh.close()

    fin = datetime.now()

    print(fin - inicio)


def procesarLineas(lineas):
    jsonvalue = []

    for linea in lineas:
        datos = json.loads(linea)

        ident = datos["id_str"]

        if datos["lang"] == "es":
            datos["_index"] = "tweets-20090624-20090626-en_es-10percent-exercise1"
            datos["_id"] = ident

            jsonvalue.append(datos)

    num_elementos = len(jsonvalue)
    resultado = helpers.bulk(es, jsonvalue, chunk_size=num_elementos, request_timeout=200)

    global contador

    contador += num_elementos
    print(contador)


if __name__ == '__main__':
    main()
