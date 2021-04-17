package br.com.estudo.heroesapi.config;

import br.com.estudo.heroesapi.util.Constants;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class HeroesData {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        Constants.DynamoDb.ENDPOINT, Constants.DynamoDb.REGION
                ))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable(Constants.Heroes.TABLE);

        Item hero = new Item()
                .withPrimaryKey("id", "1")
                .withString("name", "Mulher Maravilha")
                .withString("universe", "DC Comics")
                .withNumber("films", 3);

        PutItemOutcome outcome = table.putItem(hero);
    }
}
