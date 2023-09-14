import json # Para poder trabajar con objetos JSON

from elasticsearch import Elasticsearch
from elasticsearch import helpers

def main():

    from datetime import datetime

    inicio = datetime.now()

    # Password para el usuario 'elastic' generada por Elasticsearch
    #
    ELASTIC_PASSWORD = "u*wmb02yhThyqXjm27fa"

    # Creamos el cliente y lo conectamos a nuestro servidor
    #
    global es

    es = Elasticsearch(
        "https://localhost:9200",
        ca_certs="./http_ca.crt",
        basic_auth=("elastic", ELASTIC_PASSWORD)
    )

    # Creamos el índice
    #
    # Si no se crea explícitamente se crea al indexar el primer documento
    #
    # Debemos crearlo puesto que el mapeado por defecto (mapping) de algunos
    # campos, no es satisfactorio.
    #
    # ignore=400 hace que se ignore el error de índice ya existente
    #

    argumentos={
        "settings": {
            "analysis": {
                "analyzer": {
                    "palabras_vacias_ingles_porter": {
                        "type": "stop",
                        "stopwords": ["a","about","above","after","again",
                                    "against","all","am","an","and","any","are",
                                    "aren't","as","at","be","because","been",
                                    "before","being","below","between","both",
                                    "but","by","can't","cannot","could",
                                    "couldn't","did","didn't","do","does",
                                    "doesn't","doing","don't","down","during",
                                    "each","few","for","from","further","had",
                                    "hadn't","has","hasn't","have","haven't",
                                    "having","he","he'd","he'll","he's","her",
                                    "here","here's","hers","herself","him",
                                    "himself","his","how","how's","i","i'd",
                                    "i'll","i'm","i've","if","in","into","is",
                                    "isn't","it","it's","its","itself","let's",
                                    "me","more","most","mustn't","my","myself",
                                    "no","nor","not","of","off","on","once",
                                    "only","or","other","ought","our","ours",
                                    "ourselves","out","over","own","same",
                                    "shan't","she","she'd","she'll","she's",
                                    "should","shouldn't","so","some","such",
                                    "than","that","that's","the","their",
                                    "theirs","them","themselves","then","there",
                                    "there's","these","they","they'd","they'll",
                                    "they're","they've","this","those","through",
                                    "to","too","under","until","up","very","was",
                                    "wasn't","we","we'd","we'll","we're","we've",
                                    "were","weren't","what","what's","when",
                                    "when's","where","where's","which","while",
                                    "who","who's","whom","why","why's","with",
                                    "won't","would","wouldn't","you","you'd",
                                    "you'll","you're","you've","your","yours",
                                    "yourself","yourselves","d","ll","m","re",
                                    "s","t","ve","aren","can","couldn","didn",
                                    "doesn","don","hadn","hasn","haven","he",
                                    "here","how","i","isn","it","let","mustn",
                                    "shan","she","shouldn","that","there","they",
                                    "wasn","we","weren","what","when","where",
                                    "who","why","won","wouldn","you"]
                    }
                }
            }
        },
        "mappings": {
            "properties": {
                "created_at": {
                  "type":"date",
                  "format": "EEE MMM dd HH:mm:ss Z yyyy"
                },
                "text": {
                    "type":"text",
                    "analyzer":"palabras_vacias_ingles_porter",
                    "fielddata": "true"
                }
            }
        }
    }

    es.indices.create(index="tweets-20090624-20090626-en_es-10percent-v2",ignore=400,body=argumentos)

    # Ahora se indexan los documentos.
    # Leemos el fichero en grandes bloques
    #
    global contador
    contador = 0

    tamano = 40*1024*1024 # Para leer 40MB, tamaño estimado de manera experimental
    fh = open("../tweets-20090624-20090626-en_es-10percent.ndjson", 'rt')
    lineas = fh.readlines(tamano)
    while lineas:
      procesarLineas(lineas)
      lineas = fh.readlines(tamano)
    fh.close()

    fin = datetime.now()

    print(fin-inicio)

# Aquí indexaremos los documentos en bloques
def procesarLineas(lineas):
  jsonvalue = []

  for linea in lineas:
    datos = json.loads(linea) # Para acceder al diccionario

    ident = datos["id_str"]

    if datos["lang"]=="en":
        datos["_index"] = "tweets-20090624-20090626-en_es-10percent-v2"
        datos["_id"] = ident

        jsonvalue.append(datos)

  num_elementos = len(jsonvalue)
  resultado = helpers.bulk(es,jsonvalue,chunk_size=num_elementos,request_timeout=200)
  # Habría que procesar el resultado para ver que todo vaya bien...

  global contador

  contador += num_elementos
  print(contador)

if __name__ == '__main__':
    main()