package net.bluecow.voicebus.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.bluecow.voicebus.core.dialogflow.DialogflowHandler;
import net.bluecow.voicebus.core.dialogflow.DialogflowRequest;
import net.bluecow.voicebus.core.dialogflow.DialogflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dialogflow-webhook")
public class WebhookController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DialogflowHandler dialogflowHandler;

    @PostMapping("/handle-action")
    public DialogflowResponse handleAction(@RequestBody DialogflowRequest request) throws JsonProcessingException {
        log.debug("Got action request from Google: " + objectMapper.writeValueAsString(request));
        return dialogflowHandler.handleAction(request);
    }

}
