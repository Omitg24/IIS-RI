import json
from urllib.parse import quote

import requests


class TrendingTopic:
    def __init__(self, trending_topic):
        self.trending_topic = trending_topic
        self.id = None
        self.synonyms = None
        self.type = None
        self.find_id()
        self.find_synonyms_and_types()

    def find_id(self):
        query = self.trending_topic

        urlencoded_query = quote(query)

        r = requests.get(
            'https://www.wikidata.org/w/api.php?action=wbsearchentities&language=en&format=json&search=' + urlencoded_query)

        data = json.loads(r.text)
        data = data["search"]

        self.id = "ID_DOES_NOT_EXIST" if len(data) == 0 else data[0]["id"]

    def find_synonyms_and_types(self):
        if self.id == "ID_DOES_NOT_EXIST":
            self.synonyms = "ID_HAS_NO_SYNONYMS"
            self.type = "ID_HAS_NO_TYPE"
            return

        r = requests.get(
            "https://www.wikidata.org/w/api.php?action=wbgetentities&ids=" + self.id + "&languages=es&format=json")

        data = json.loads(r.text)
        self.synonyms = self.parse_synonyms(data["entities"][self.id]["aliases"])
        self.type = self.parse_type(data["entities"][self.id]["claims"])

    @staticmethod
    def parse_type(type_data):
        if "P31" not in type_data:
            return "ID_HAS_NO_TYPE"

        return type_data["P31"][0]["type"]

    @staticmethod
    def parse_synonyms(synonyms_data):
        if len(synonyms_data) == 0:
            return "ID_HAS_NO_SYNONYMS"

        return " ".join([s["value"] for s in synonyms_data["es"]])

    def __str__(self):
        return f"{self.id}\t{self.trending_topic}\t{self.synonyms}\t{self.type}"
