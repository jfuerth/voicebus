package net.bluecow.voicebus.core.dialogflow.format;

import net.bluecow.voicebus.core.nextbus.NextbusPredictionResult;

public interface PredictionFormatter {
    String format(NextbusPredictionResult result, int predictionsPerRoute);
}
