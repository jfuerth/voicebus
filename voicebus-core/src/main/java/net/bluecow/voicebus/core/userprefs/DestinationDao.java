package net.bluecow.voicebus.core.userprefs;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DestinationDao {

    // TODO dependency injection!
    private static final DynamoDBMapper mapper = new DynamoDbConnection().mapper();

    public List<Destination> findByUser(String userId) {
        DynamoDBQueryExpression<Destination> query = new DynamoDBQueryExpression<>();
        Destination example = new Destination();
        example.setUserId(userId);
        query.setHashKeyValues(example);
        List<Destination> destinations = mapper.query(Destination.class, query);
        log.debug("Found {} destinations for user {}", destinations.size(), userId);
        return destinations;
    }

    public void save(Destination destination) {
        if (destination.getUserId() == null || destination.getUserId().trim().length() == 0) {
            throw new IllegalArgumentException("Can't save a destination without a userId");
        }
        if (destination.getPrimaryName() == null || destination.getPrimaryName().trim().length() == 0) {
            throw new IllegalArgumentException("Can't save a destination without a primaryName");
        }
        mapper.save(destination);
    }
}
