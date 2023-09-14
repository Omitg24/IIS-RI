import json # Para poder trabajar con objetos JSON

from elasticsearch import Elasticsearch
import elasticsearch.helpers

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

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        q="cristiano ronaldo"
    )

    print(str(results["hits"]["total"]) + " resultados para la query q=\"cristiano ronaldo\"")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        q="iran"
    )


    print(str(results["hits"]["total"]) + " resultados para la query q=\"iran\"")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        q="text:iran AND lang:en"
    )

    print(str(results["hits"]["total"]) + " resultados para la query q=\"text:iran AND lang:en\"")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        q="text:iran AND lang:es"
    )

    print(str(results["hits"]["total"]) + " resultados para la query q=\"text:iran AND lang:es\"")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        q="user_id_str:2467791"
    )

    print(str(results["hits"]["total"]) + " resultados para la query q=\"user_id_str:2467791\"")

    results = es.count(
        index="tweets-20090624-20090626-en_es-10percent",
        q="iran"
    )

    print(str(results["count"])+ " resultados para la query q=\"iran\"")

    results = es.count(
        index="tweets-20090624-20090626-en_es-10percent",
        q="text:iran AND lang:en"
    )

    print(str(results["count"])+ " resultados para la query q=\"text:iran AND lang:en\"")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        body = {
                "size" : 0,
                "track_total_hits" : "true",
                "query" : {
                    "match" : {
                        "text": "iran"
	               }
	           }
        }
    )

    print(str(results["hits"]["total"]) + " resultados para la query \"iran\" enviada como una request.")


    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        body = {
                "size" : 0,
                "track_total_hits" : "true",
                "query" : {
                    "query_string" : {
                        "query": "text:iran AND lang:en"
                    }
	           }
        }
    )

    print(str(results["hits"]["total"]) + " resultados para la query \"text:iran AND lang:en\" enviada como una request.")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        body = {
            "track_total_hits" : "true",
            "query" : {
                "simple_query_string" : {
                    "query": "china + google"
	           }
	       }
        }
    )

    print(str(results["hits"]["total"]) + " resultados para la consulta simple_query_string \"china + google\" enviada como una request.")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        body = {
            "track_total_hits" : "true",
            "query" : {
                "simple_query_string" : {
                    "query": "\"liu xiaobo\""
	           }
	       }
        }
    )

    print(str(results["hits"]["total"]) + " resultados para la consulta simple_query_string de frase exacta \"liu xiaobo\" enviada como una request.")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        body = {
            "track_total_hits" : "true",
            "query" : {
                "simple_query_string" : {
                    "query": "\"south waziristan\" | pakistan"
	           }
	       }
        }
    )

    print(str(results["hits"]["total"]) + " resultados para la consulta simple_query_string \"south waziristan\" | pakistan enviada como una request.")

    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent",
        body = {
            "track_total_hits" : "true",
            "query" : {
                "simple_query_string" : {
                    "query": "\"michael jackson\" | \"king of pop\""
	           }
	       }
        }
    )

    print(str(results["hits"]["total"]) + " resultados para la consulta simple_query_string \"michael jackson\" | \"king of pop\" enviada como una request.")

if __name__ == '__main__':
    main()
