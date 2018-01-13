package net.bluecow.voicebus.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.bluecow.voicebus.core.dialogflow.DialogflowHandler;
import net.bluecow.voicebus.core.dialogflow.DialogflowRequest;
import net.bluecow.voicebus.core.dialogflow.DialogflowResponse;
import net.bluecow.voicebus.core.nextbus.NextbusClient;
import net.bluecow.voicebus.core.nextbus.NextbusClientFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class VoicebusLambdaFunction implements RequestStreamHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final NextbusClient nextbusClient = new NextbusClientFactory().nextbusClient();
    private static final DialogflowHandler dialogflowHandler = new DialogflowHandler(nextbusClient);

    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        ApiGatewayRequest wrapper = objectMapper.readValue(input, ApiGatewayRequest.class);
        DialogflowRequest request = objectMapper.readValue(wrapper.getBody(), DialogflowRequest.class);

        DialogflowResponse dialogflowResponse = dialogflowHandler.handleAction(request);

        ApiGatewayResponse gatewayResponse = new ApiGatewayResponse();
        gatewayResponse.setStatusCode("200");
        gatewayResponse.setBody(objectMapper.writeValueAsString(dialogflowResponse));
        output.write(objectMapper.writeValueAsBytes(gatewayResponse));
    }
}
