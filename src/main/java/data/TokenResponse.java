package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class TokenResponse {

    private String token;
    private String expires;
    private String status;
    private String result;

    public String getToken() {
        return token;
    }
    
    public String getEpires() {
        return expires;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}