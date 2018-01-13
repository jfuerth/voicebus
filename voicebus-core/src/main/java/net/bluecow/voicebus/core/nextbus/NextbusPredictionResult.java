package net.bluecow.voicebus.core.nextbus;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class NextbusPredictionResult {

    private String agencyTitle;
    private String routeTitle;
    private String routeTag;
    private String stopTitle;
    private String stopTag;
    private String directionTitle;
    private List<NextbusPrediction> predictions;
}
