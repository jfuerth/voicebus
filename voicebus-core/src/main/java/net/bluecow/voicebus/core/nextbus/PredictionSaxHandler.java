package net.bluecow.voicebus.core.nextbus;

import feign.sax.SAXDecoder;
import lombok.extern.slf4j.Slf4j;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class PredictionSaxHandler extends DefaultHandler implements SAXDecoder.ContentHandlerWithResult<NextbusPredictionResult> {

    private NextbusPredictionResult.NextbusPredictionResultBuilder root;
    private List<NextbusPrediction> predictions;
    private NextbusPredictionResult result;

    @Override
    public void startDocument() throws SAXException {
        log.debug("startDocument");
        root = NextbusPredictionResult.builder();
        predictions = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        log.debug("startElement {}", qName);
        switch (qName) {
            case "predictions":
                root.agencyTitle(attributes.getValue("agencyTitle"));
                root.routeTitle(attributes.getValue("routeTitle"));
                root.routeTag(attributes.getValue("routeTag"));
                root.stopTitle(attributes.getValue("stopTitle"));
                root.stopTag(attributes.getValue("stopTag"));
                break;
            case "direction":
                root.directionTitle(attributes.getValue("title"));
                break;
            case "prediction":
                predictions.add(NextbusPrediction.builder()
                        .time(Instant.ofEpochMilli(Long.valueOf(attributes.getValue("epochTime"))))
                        .seconds(Integer.valueOf(attributes.getValue("seconds")))
                        .minutes(Integer.valueOf(attributes.getValue("minutes")))
                        .branch(attributes.getValue("branch"))
                        .dirTag(attributes.getValue("dirTag"))
                        .vehicle(attributes.getValue("vehicle"))
                        .block(attributes.getValue("block"))
                        .tripTag(attributes.getValue("tripTag"))
                        .departure(falseNull(attributes.getValue("isDeparture")))
                        .affectedByLayover(falseNull(attributes.getValue("affectedByLayover")))
                        .scheduleBased(falseNull(attributes.getValue("isScheduleBased")))
                        .delayed(falseNull(attributes.getValue("delayed")))
                        .build());
                break;
        }
    }

    private static boolean falseNull(String in) {
        if (in != null) {
            return Boolean.valueOf(in);
        }
        return false;
    }

    @Override
    public void endDocument() throws SAXException {
        log.debug("endDocument");
        root.predictions(Collections.unmodifiableList(predictions));
        result = root.build();
    }

    @Override
    public NextbusPredictionResult result() {
        return result;
    }
}
