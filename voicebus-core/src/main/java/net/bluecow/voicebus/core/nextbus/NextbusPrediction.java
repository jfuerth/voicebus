package net.bluecow.voicebus.core.nextbus;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class NextbusPrediction {
    private Instant time;
    private int seconds;
    private int minutes;
    private boolean departure;
    private boolean affectedByLayover;
    private String branch;
    private boolean scheduleBased;
    private boolean delayed;
    private String dirTag;
    private String vehicle;
    private String block;
    private String tripTag;
}
