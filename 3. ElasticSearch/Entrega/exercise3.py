import json
from datetime import datetime

from elasticsearch import Elasticsearch
from elasticsearch import helpers


def main():
    # Password para el usuario 'lectura' asignada por nosotros
    #
    readonly_password = "abretesesamo"

    # Creamos el cliente y lo conectamos a nuestro servidor
    #
    global es

    es = Elasticsearch(
        "https://localhost:9200",
        ca_certs="./http_ca.crt",
        basic_auth=("lectura", readonly_password)
    )

    body = input("Introduzca la consulta a buscar: ")  # {"query": {"simple_query_string": {"query": "text: taliban"}}}
    expansion = input("Introduzca el número con el que expandir el tema (por ejemplo \"20\"): ")

    print("\n##########################################################################")
    print("# DOCUMENTOS RELACIONADOS CON LA CONSULTA INTRODUCIDA POR PARÁMETRO USANDO MLT")
    print("##########################################################################")

    inicio = datetime.now()

    documents = find_documents(json.loads(body))

    fin = datetime.now()

    print("ELAPSED TIMES: ", fin - inicio)

    print("\n##########################################################################")
    print("# EXPANSIÓN DE LA CONSULTA DE LA CONSULTA INTRODUCIDA POR PARÁMETRO USANDO MLT")
    print("##########################################################################")

    inicio = datetime.now()

    results = find_tweets(documents, expansion)

    f = open("exercise3.tsv", "wb")
    i = 0
    for hit in results:
        author = hit["_source"]["user_id_str"]
        created_at = hit["_source"]["created_at"]
        text = hit["_source"]["text"]

        text = text.replace("\n", " ") + "\n"
        line = author + "\t" + created_at + "\t" + text
        print(line)
        f.write(line.encode("UTF-8"))
        i += 1
        if i == 20:
            break

    f.close()

    fin = datetime.now()

    print("ELAPSED TIMES: ", fin - inicio)


def find_documents(body):
    # Búsqueda de documentos
    #
    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent-exercise2",
        body=body
    )

    documents = []

    for i in results["hits"]["hits"]:
        documents.append(i["_source"]["text"])
        print("\tterms: " + i["_source"]["text"])

    return documents


def find_tweets(documents, expansion):
    # Búsqueda de tweets
    #
    return helpers.scan(es,
                        index="tweets-20090624-20090626-en_es-10percent-exercise2",
                        query={
                            "query": {
                                "more_like_this": {
                                    "fields": ["text"],
                                    "like": documents,
                                    "min_term_freq": 1,
                                    "max_query_terms": expansion
                                }
                            }
                        }
                        )


if __name__ == '__main__':
    main()
