package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class LoginResponse {

    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}