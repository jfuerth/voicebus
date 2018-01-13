package net.bluecow.voicebus.core.dialogflow.format;

import net.bluecow.voicebus.core.nextbus.NextbusPredictionResult;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PredictionSpeechFormatterTest {

    PredictionSpeechFormatter formatter = new PredictionSpeechFormatter();

    @Test
    public void shouldFindEastDirectionIn506EastTitle() throws Exception {

        String direction = formatter.formatRouteDirection(NextbusPredictionResult.builder()
                .routeTag("506")
                .directionTitle("East - 506 Carlton towards Main Street Station")
                .build());

        assertEquals("5 oh 6 east", direction);
    }
}