package bookStoreAPI;

import data.LoginResponse;
import data.TokenResponse;
import data.UserResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;


public class AccountSteps extends Auth {

    private final String baseURI = "https://demoqa.com/Account/v1/";

    public enum Status {     SUCCESS,
        FAILED;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }
    @Step("generte token")
    private void generateToken() {
        setRequest();
        Response response = requestSpec.post("GenerateToken");
        System.out.println("POST GenerateToken: " + response.statusLine());
        System.out.println("Token: " + response.asPrettyString());
        tokenResponse = response.getBody().as(TokenResponse.class);
        Assert.assertEquals(tokenResponse.getStatus(), Status.SUCCESS.toString(), tokenResponse.getResult());
    }
    
    @Step("login")
    public LoginResponse login() {
        generateToken();
        setRequest();
        Response response = requestSpec.post("Login");
        System.out.println("POST Login: " + response.statusLine());
        System.out.println(response.asPrettyString());
        return response.getBody().as(LoginResponse.class);
    }

    @Step("Get Userdata")
    public UserResponse getUserData(String userId) {
        configure(baseURI, true);
        Response response = requestSpec.get("User/" + userId);
        System.out.println("GET User: " + response.statusLine());
        System.out.println("GET User: " + response.asPrettyString());
        return response.getBody().as(UserResponse.class);
    }
    @Step("Set Requst")
    private void setRequest() {
        configure(baseURI, false);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", manager.getUsername());
        requestParams.put("password", manager.getPassword());
        requestSpec.body(requestParams.toString());
    }
}