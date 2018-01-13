package net.bluecow.voicebus.core.dialogflow;

import net.bluecow.voicebus.core.dialogflow.format.PredictionFormatter;
import net.bluecow.voicebus.core.SillyWordMaker;
import net.bluecow.voicebus.core.dialogflow.format.PredictionSpeechFormatter;
import net.bluecow.voicebus.core.dialogflow.format.PredictionTextFormatter;
import net.bluecow.voicebus.core.nextbus.NextbusClient;
import net.bluecow.voicebus.core.nextbus.NextbusPrediction;
import net.bluecow.voicebus.core.nextbus.NextbusPredictionResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class DialogflowHandler {

    private NextbusClient nextbusClient;
    private PredictionFormatter predictionSpeechFormatter = new PredictionSpeechFormatter();
    private PredictionFormatter predictionTextFormatter = new PredictionTextFormatter();

    public DialogflowHandler(NextbusClient nextbusClient) {
        this.nextbusClient = nextbusClient;
    }

    public DialogflowResponse handleAction(DialogflowRequest request) {
        DialogflowRequest.Result nlpResult = request.getResult();
        switch (nlpResult.getAction()) {
            case "prediction":
                return getPredictions(request);
            case "sillyword":
                return makeSillyWords((String) nlpResult.getParameters().get("wordCount"));
        }

        return DialogflowResponse.builder()
                .speech("Unknown action " + nlpResult.getAction())
                .displayText("Unknown action " + nlpResult.getAction())
                .source("internal")
                .build();
    }

    private DialogflowResponse makeSillyWords(String count) {
        Random r = new Random();
        String sillyWords = IntStream.range(0, parseInt(count, 1)).mapToObj(i -> SillyWordMaker.makeSillyWord(r)).collect(joining(". "));
        return DialogflowResponse.builder()
                .speech(sillyWords)
                .displayText(sillyWords)
                .source("SillyWordMaker")
                .build();
    }

    private Integer parseInt(String count, int defaultValue) {
        try {
            return Integer.valueOf(count);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private DialogflowResponse getPredictions(DialogflowRequest request) {
        List<NextbusPredictionResult> predictionResults = new ArrayList<>();
        String destination = (String) request.getResult().getParameters().get("destination");
        switch (destination) {
            case "east":
                predictionResults.add(nextbusClient.predictions("ttc", "505", "2954"));
                predictionResults.add(nextbusClient.predictions("ttc", "506", "8999"));
                predictionResults.add(nextbusClient.predictions("ttc", "504", "14808"));
                break;
            case "west":
                predictionResults.add(nextbusClient.predictions("ttc", "505", "5094"));
                predictionResults.add(nextbusClient.predictions("ttc", "506", "5094"));
                predictionResults.add(nextbusClient.predictions("ttc", "504", "9383"));
                break;
            default:
        }


        String spokenPredictions = predictionResults.stream()
                .sorted(soonestFirst())
                .map(result -> predictionSpeechFormatter.format(result, 2))
                .collect(joining(". "));

        String printedPredictions = predictionResults.stream()
                .sorted(soonestFirst())
                .map(result -> predictionTextFormatter.format(result, 2))
                .collect(joining(". "));

        return DialogflowResponse.builder()
                .speech(spokenPredictions)
                .displayText(printedPredictions)
                .source("nextbus")
                .build();
    }

    private Comparator<NextbusPredictionResult> soonestFirst() {
        return Comparator.comparing(predictions -> {
            List<NextbusPrediction> predictionList = predictions.getPredictions();
            if (predictionList != null && predictionList.size() > 0) {
                return predictionList.get(0).getSeconds();
            } else {
                return 9999;
            }
        });
    }

}
