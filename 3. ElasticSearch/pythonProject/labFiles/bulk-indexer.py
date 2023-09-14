# Para poder usar la función print e imprimir sin saltos de línea
from __future__ import print_function

import json # Para poder trabajar con objetos JSON

from elasticsearch import Elasticsearch
from elasticsearch import helpers

def main():
    from datetime import datetime

    inicio = datetime.now()

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

    # Creamos el índice
    #
    # Si no se crea explícitamente se crea al indexar el primer documento
    #
    # Debemos crearlo puesto que el mapeado por defecto (mapping) de algunos
    # campos, no es satisfactorio.
    #
    # ignore=400 hace que se ignore el error de índice ya existente
    #
    es.indices.create(index="tweets-20090624-20090626-en_es-10percent",ignore=400)

    # Se especifican los tipos que no sirven por defecto...
    #
    argumentos={
      "properties": {
        "created_at": {
          "type":"date",
          "format": "EEE MMM dd HH:mm:ss Z yyyy"
        }
      }
    }
    es.indices.put_mapping(index="tweets-20090624-20090626-en_es-10percent",body=argumentos)

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

    datos["_index"] = "tweets-20090624-20090626-en_es-10percent"
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