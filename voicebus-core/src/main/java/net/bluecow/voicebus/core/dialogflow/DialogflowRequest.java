package net.bluecow.voicebus.core.dialogflow;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class DialogflowRequest {

    private String id;
    private Instant timestamp;
    private String lang;
    private Map<String, Object> status;
    private String sessionId;
    private Result result;

    @JsonIgnore
    private final Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String key, Object value) {
        additionalProperties.put(key, value);
    }

    @Data
    public static class Result {
        private String source;
        private String resolvedQuery;
        private String speech;
        private String action;
        private boolean actionIncomplete;
        private List<Object> contexts;
        private Map<String, Object> metadata;
        private Map<String, Object> parameters;
    }
}
