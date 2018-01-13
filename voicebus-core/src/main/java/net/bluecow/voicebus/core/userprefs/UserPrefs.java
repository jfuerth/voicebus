package net.bluecow.voicebus.core.userprefs;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "VoicebusUserPrefs")
public class UserPrefs {

    @DynamoDBHashKey
    private String userId;

    private String defaultAgency;

    private double homeLat;
    private double homeLon;

    private int numberOfPredictions;
}
