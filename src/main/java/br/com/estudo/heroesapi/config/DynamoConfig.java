package br.com.estudo.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories
public class DynamoConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;

    @Value("${aws.access.key.id}")
    private String awsAccessKey;

    @Value("${aws.secret.access.key}")
    private String awsSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials) {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(awsCredentials);
        if (!StringUtils.isEmpty(endpoint)) {
            amazonDynamoDB.setEndpoint(endpoint);
        }
        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }
}
