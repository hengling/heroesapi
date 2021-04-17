package br.com.estudo.heroesapi.util;

public class Constants {

    public static class DynamoDb {
        public static final String ENDPOINT = "http://localhost:8000";
        public static final String REGION = "us-east-1";
    }

    public static class Heroes {
        public static final String ENDPOINT = "/heroes";
        public static final String TABLE = "Heroes_Table";
    }

}
