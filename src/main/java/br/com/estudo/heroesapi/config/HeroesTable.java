package br.com.estudo.heroesapi.config;

import br.com.estudo.heroesapi.util.Constants;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import java.util.List;

public class HeroesTable {
    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        Constants.DynamoDb.ENDPOINT, Constants.DynamoDb.REGION
                ))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        try {
            dynamoDB.createTable(
                    Constants.Heroes.TABLE,
                    List.of(new KeySchemaElement("id", KeyType.HASH)),
                    List.of(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L)
            ).waitForActive();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
