package net.bluecow.voicebus.lambda;

import java.util.HashMap;
import java.util.Map;

public class ApiGatewayResponse {
    private String statusCode;
    private Map<String, String> headers = new HashMap<String, String>();
    private String body;
    private boolean isBase64Encoded;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    @Override
    public String toString() {
        return "ApiGatewayResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", isBase64Encoded=" + isBase64Encoded +
                '}';
    }
}
