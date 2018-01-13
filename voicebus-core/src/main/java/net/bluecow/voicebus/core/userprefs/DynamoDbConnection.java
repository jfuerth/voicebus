package net.bluecow.voicebus.core.userprefs;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDbConnection {

    private final DynamoDBMapper mapper;

    DynamoDbConnection() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(Regions.US_EAST_1));
        mapper = new DynamoDBMapper(client);
    }

    public DynamoDBMapper mapper() {
        return mapper;
    }

}