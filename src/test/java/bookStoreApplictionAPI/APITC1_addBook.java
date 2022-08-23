package bookStoreApplictionAPI;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import bookStoreAPI.AccountSteps;
import bookStoreAPI.BookStoreSteps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import io.qameta.allure.Story;


@Story("API Test - Add book")
public class APITC1_addBook {

    private AccountSteps accountSteps;
    private BookStoreSteps bookStoreSteps;
    private String userId;
    Map<String, String> book;

    @BeforeTest(alwaysRun = true)
    public void init() {
    	
        accountSteps = new AccountSteps();
        bookStoreSteps = new BookStoreSteps();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        book = loginAndAddBook();
        System.out.println("Book Added " +book);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        bookStoreSteps.deleteBooks(userId);
        
    }
    

    @Test(priority = 1)
    @Description("Add book to collection")
    public void addBookTest() {
        Assert.assertTrue(findBookInCollection(book.get("isbn")), "Book not added to collection:");
        System.out.println("Book added to collection");
    }

    private Map<String, String> loginAndAddBook() {
        userId = accountSteps.login().getUserId();
        Assert.assertEquals(bookStoreSteps.deleteBooks(userId), 204, "Can't prepare collection:");
        List<Map<String, String>> booksList = bookStoreSteps.getBooks();
        Map<String, String> randomBook = booksList.get(new Random().nextInt(booksList.size()));
        int responseCode =bookStoreSteps.addBook(userId, randomBook.get("isbn"));
        Assert.assertEquals(responseCode, 201, "Can't add book to collection:");
        return randomBook;
    }

    private boolean findBookInCollection(String isbn) {
        List<Map<String, String>> books = accountSteps.getUserData(userId).getBooks();
        System.out.println("Books in collection " + books);
        for (Map<String, String> item : books) {
            if (item.get("isbn").equals(isbn)) {
                return true;
            }
        }
        return false;
    }
}