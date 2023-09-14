from datetime import datetime

from elasticsearch import Elasticsearch
from tqdm import tqdm

from TrendingTopic import TrendingTopic


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

    print("\n##########################################################################")
    print("# GENERACIÓN DE TRENDING TOPICS")
    print("##########################################################################\n")

    inicio = datetime.now()

    # Búsqueda de los trending topics
    #
    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent-exercise1",
        body={
            "size": 0,
            "query": {
                "query_string": {
                    "query": "lang:es"
                }
            },
            "aggs": {
                "Trending topics per hour": {
                    "date_histogram": {
                        "field": "created_at",
                        "fixed_interval": "1h"
                    },
                    "aggs": {
                        "Trending topics": {
                            "significant_terms": {
                                "field": "text",
                                "size": 50,
                                "gnd": {}
                            }
                        }
                    }
                }
            }
        },
        request_timeout=400)

    trending_topics_per_hour = results["aggregations"]["Trending topics per hour"]["buckets"]

    trending_topics = {}

    # Generación del diccionario con la fecha y los trending topics (valor, sinonimos y tipo)
    #
    for ttph in tqdm(trending_topics_per_hour):
        date = ttph["key_as_string"]
        trending_topics.update({date: [TrendingTopic(tt.get("key")) for tt in ttph["Trending topics"]["buckets"]]})

    fin = datetime.now()
    print("ELAPSED TIMES: ", fin - inicio)

    print("\n##########################################################################")
    print("# SUBIDA A FICHERO:")
    print("##########################################################################\n")

    inicio = datetime.now()

    # Subida al fichero
    #
    with open("exercise1.tsv", "wb") as f:
        for date in trending_topics:
            line = date + "\n\t" + "\n\t".join([t.__str__() for t in trending_topics[date]]) + "\n"
            print(line)
            f.write(line.encode("UTF-8"))

    fin = datetime.now()
    print("ELAPSED TIMES: ", fin - inicio)


if __name__ == '__main__':
    main()
