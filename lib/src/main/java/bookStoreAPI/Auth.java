package bookStoreAPI;

import core.TestManager;
import data.TokenResponse;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Auth {

    protected final TestManager manager;
    protected RequestSpecification requestSpec;
    protected static TokenResponse tokenResponse;

    public Auth() {
        manager = TestManager.getInstance();
    }

    protected void configure(String baseURI, boolean auth) {
        RestAssured.baseURI = baseURI;
        if (auth) {
            Assert.assertNotNull(tokenResponse, "Token was not generated:");
            Assert.assertNotNull(tokenResponse.getToken(), "This user has not token:");
            requestSpec = given().headers("Authorization" , "Bearer " + tokenResponse.getToken());
        } else {
            requestSpec = given();
        }
        requestSpec.header("Content-Type", "application/json");
    }
}