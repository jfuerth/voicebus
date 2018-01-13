package net.bluecow.voicebus.core.nextbus;

import feign.Param;
import feign.RequestLine;

public interface NextbusClient {

    @RequestLine("GET /service/publicXMLFeed?command=predictions&a={agency}&r={route}&s={stopTag}")
    NextbusPredictionResult predictions(@Param("agency") String agency, @Param("route") String route, @Param("stopTag") String stopTag);
}
