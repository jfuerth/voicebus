package net.bluecow.voicebus.boot;

import net.bluecow.voicebus.core.dialogflow.format.PredictionFormatter;
import net.bluecow.voicebus.core.nextbus.NextbusClient;
import net.bluecow.voicebus.core.nextbus.NextbusPredictionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoicebusController {

    @Autowired
    private NextbusClient nextbusClient;

    @GetMapping
    public String getPrediction() {
        NextbusPredictionResult result = nextbusClient.predictions("ttc", "505", "2954");
        return result.getRouteTag() + " " + result.getDirectionTitle() + " " + PredictionFormatter.format(result);
    }
}
