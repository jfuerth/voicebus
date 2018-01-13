package net.bluecow.voicebus.core.dialogflow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DialogflowResponse {
    private String speech;
    private String displayText;
    private Map<String, Object> data;
    private List<Object> contextOut;
    private String source;
}
