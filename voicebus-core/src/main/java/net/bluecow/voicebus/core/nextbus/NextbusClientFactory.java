package net.bluecow.voicebus.core.nextbus;

import feign.Feign;
import feign.Logger;
import feign.sax.SAXDecoder;
import feign.slf4j.Slf4jLogger;

public class NextbusClientFactory {

    public NextbusClient nextbusClient() {
        return Feign.builder()
                .logger(new Slf4jLogger(NextbusClient.class))
                .logLevel(Logger.Level.BASIC)
                .decoder(SAXDecoder.builder()
                        .registerContentHandler(PredictionSaxHandler.class)
                        .build())
                .target(NextbusClient.class, "http://webservices.nextbus.com/");
    }
}
