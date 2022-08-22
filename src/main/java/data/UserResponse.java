package data;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class UserResponse {

    private String userId;
    private String username;
    private List<Map<String, String>> books;

    public String getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public List<Map<String, String>> getBooks() {
        return books;
    }
    
    
}