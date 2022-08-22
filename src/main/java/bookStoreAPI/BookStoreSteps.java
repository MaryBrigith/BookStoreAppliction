package bookStoreAPI;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class BookStoreSteps extends Auth {

	private final String baseUrl = "https://demoqa.com/BookStore/v1/";
	String jsonString;
	List<Map<String, String>> booksOfUsernow;

    @Step("Get Books")
    public List<Map<String, String>> getBooks() {
        configure(baseUrl, false);
        Response response = requestSpec.get("Books");
        System.out.println("GET Books: " + response.statusLine());
        jsonString = response.asString();
        booksOfUsernow = JsonPath.from(jsonString).get("books");   
        return booksOfUsernow;
    }

    @Step("Add a Book")
    public int addBook(String userId, String isbn) {

        configure(baseUrl, true);
        System.out.println("userId"+ userId+ " isbn"+isbn);
        requestSpec.body("{ \"userId\": \"" + userId + "\", " +
                "\"collectionOfIsbns\": [ { \"isbn\": \"" + isbn + "\" } ]}");
        Response response = requestSpec.post("Books");
        System.out.println("POST Books: " + response.statusLine());
        System.out.println("POST Books: " + response.asPrettyString());
        return response.getStatusCode();
    }

    @Step("Delete Books")
    public int deleteBooks(String userId) {
        configure(baseUrl, true);
        Response response = requestSpec.delete("Books/?UserId=" + userId);
        System.out.println("DELETE Books: " + response.statusLine());
        System.out.println("DELETE Books: " + response.asPrettyString());
        return response.getStatusCode();
    }

    @Step("Delete a Book")
    public int deleteBook(String userId, String isbn) {
        setRequest(userId, isbn);
        Response response = requestSpec.delete("Book");
        System.out.println("DELETE Book: " + response.statusLine());
        return response.getStatusCode();
    }

    @Step("Replace a Book")
    public int replaceBook(String userId, String isbnFrom, String isbnTo) {
        setRequest(userId, isbnTo);
        Response response = requestSpec.put("Books/" + isbnFrom);
        System.out.println("PUT Books: " + response.statusLine());
        return response.getStatusCode();
    }

    @Step("Set Rquest")
    private void setRequest(String userId, String isbn) {
        configure(baseUrl, true);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userId", userId);
        requestParams.put("isbn", isbn);
        requestSpec.body(requestParams.toString());
    }
}
