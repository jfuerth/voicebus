package net.bluecow.voicebus.core.userprefs;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPrefsDao {

    // TODO dependency injection!
    private static final DynamoDBMapper mapper = new DynamoDbConnection().mapper();

    public UserPrefs findByUser(String userId) {
        UserPrefs prefs = mapper.load(UserPrefs.class, userId);
        log.debug("Prefs for user {} are {}", userId, prefs);
        return prefs;
    }

}
