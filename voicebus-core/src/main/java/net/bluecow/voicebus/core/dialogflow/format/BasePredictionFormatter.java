package net.bluecow.voicebus.core.dialogflow.format;

import lombok.extern.slf4j.Slf4j;
import net.bluecow.voicebus.core.nextbus.NextbusPredictionResult;

import static java.util.stream.Collectors.joining;

@Slf4j
public abstract class BasePredictionFormatter implements PredictionFormatter {

    @Override
    public String format(NextbusPredictionResult result, int predictionsPerRoute) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatRouteDirection(result).trim());
        sb.append(" ");
        sb.append(formatPredictions(result, predictionsPerRoute).trim());
        sb.append(".");

        String phrase = sb.toString();
        log.debug("Formatted predictions: {}", phrase);

        return phrase;
    }

    protected String formatPredictions(NextbusPredictionResult result, int limit) {
        return result.getPredictions().stream()
                .limit(limit)
                .map(p -> "" +
                        (p.getSeconds() <= 60 ? unitsPhrase(p.getSeconds(), "second") : unitsPhrase(p.getMinutes(), "minute")) +
                        (p.isAffectedByLayover() ? " plus layover" : "")).collect(joining("; "));
    }

    protected String formatRouteDirection(NextbusPredictionResult result) {
        String direction;
        if (result.getDirectionTitle().toLowerCase().contains("east ")) {
            direction = "east";
        } else if (result.getDirectionTitle().toLowerCase().contains("west ")) {
            direction = "west";
        } else if (result.getDirectionTitle().toLowerCase().contains("south ")) {
            direction = "south";
        } else if (result.getDirectionTitle().toLowerCase().contains("north ")) {
            direction = "north";
        } else {
            log.debug("Couldn't figure out direction for {}", result.getDirectionTitle());
            direction = result.getDirectionTitle().replace(result.getRouteTag(), "");
        }
        return routeName(result) + " " + direction;
    }

    protected String routeName(NextbusPredictionResult result) {
        return result.getRouteTag();
    }

    protected String unitsPhrase(int amount, String singularUnit) {
        return unitsPhrase(amount, singularUnit, singularUnit + "s");

    }

    protected String unitsPhrase(int amount, String singularUnit, String pluralUnit) {
        if (amount == 1) {
            return amount + " " + singularUnit;
        } else {
            return amount + " " + pluralUnit;
        }
    }
}
