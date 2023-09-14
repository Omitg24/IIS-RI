import json # Para poder trabajar con objetos JSON

from elasticsearch import Elasticsearch

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
    # Leemos el fichero línea a línea pues puede llegar a ser muy grande

    global contador
    contador = 0

    fh = open("../tweets-20090624-20090626-en_es-10percent.ndjson", 'rt')
    linea = fh.readline()
    while linea:
        procesarEntrada(linea)
        linea = fh.readline()
    fh.close()

# Aquí indexaremos los documentos
def procesarEntrada(linea):
    datos=json.loads(linea) # Para acceder al diccionario

    ident=datos["id_str"]

    es.index(
        index="tweets-20090624-20090626-en_es-10percent",
        id=ident,
        body=linea
    )

    # Habría que procesar el resultado para verificar que el documento ha sido
    # realmente indexado

    global contador

    contador += 1
    print(contador)

if __name__ == '__main__':
    main()