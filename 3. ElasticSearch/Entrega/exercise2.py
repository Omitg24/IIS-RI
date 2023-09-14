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

    word = input("Introduzca el tema a buscar (por ejemplo \"taliban\"): ")
    meter = input("Introduzca la métrica a utilizar (por ejemplo \"jlh\"): ")
    expansion = input("Introduzca el número con el que expandir el tema (por ejemplo \"5\"): ")

    print("\n##########################################################################")
    print("# TERMINOS SIGNIFICATIVOS RELACIONADOS CON \"" + word + "\" CON METRICA " + meter)
    print("##########################################################################")

    inicio = datetime.now()

    topics = find_topics(word, meter, expansion)

    fin = datetime.now()

    print("ELAPSED TIMES: ", fin - inicio)

    print("\n##########################################################################")
    print("# BÚSQUEDA DE LA EXPANSIÓN DE LA CONSULTA DEL \"" + word + "\" CON METRICA " + meter)
    print("##########################################################################")

    inicio = datetime.now()

    results = expand_query(word, topics)

    file_name = "exercise2" + word.capitalize() + meter.upper() + expansion + ".tsv"
    f = open(file_name, "wb")
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


def find_topics(word, meter, expansion):
    # Búsqueda de los temas
    #
    results = es.search(
        index="tweets-20090624-20090626-en_es-10percent-exercise2",
        body={
            "size": 0,
            "query": {
                "query_string": {
                    "query": "text: (" + word + ")"
                }
            },
            "aggs": {
                "Significant Terms": {
                    "significant_terms": {
                        "field": "text",
                        "size": expansion,
                        meter: {}
                    }
                }
            }
        }
    )

    significant_terms = results["aggregations"]["Significant Terms"]["buckets"]

    significant_terms_set = set([])
    topics = ""
    for i in significant_terms:
        key = i.get("key")
        topics += " OR " + key
        significant_terms_set.add(key)
        print("\tterms: " + key)

    return topics


def expand_query(word, topics):
    # Expansión de la consulta
    #
    return helpers.scan(es,
                        index="tweets-20090624-20090626-en_es-10percent-exercise2",
                        query={
                            "size": 0,
                            "query": {
                                "query_string": {
                                    "query": "text:((" + word + ")" + topics + ")",
                                    "default_operator": "AND"
                                }
                            }
                        }
                        )


if __name__ == '__main__':
    main()
