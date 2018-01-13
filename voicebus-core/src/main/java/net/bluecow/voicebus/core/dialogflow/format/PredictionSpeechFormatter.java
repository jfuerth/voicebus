package net.bluecow.voicebus.core.dialogflow.format;

import net.bluecow.voicebus.core.nextbus.NextbusPredictionResult;

import static java.util.stream.Collectors.joining;

public class PredictionSpeechFormatter extends BasePredictionFormatter {

    @Override
    protected String routeName(NextbusPredictionResult result) {
        if (result.getRouteTag().length() <= 4) {
            return result.getRouteTag().chars().mapToObj(c -> {
                if (c == '0') {
                    return "oh";
                } else {
                    return String.valueOf((char) c);
                }
            }).collect(joining(" "));
        }
        return result.getRouteTag();

    }
}
