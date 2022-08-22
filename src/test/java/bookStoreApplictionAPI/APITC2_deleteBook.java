package bookStoreApplictionAPI;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import bookStoreAPI.AccountSteps;
import bookStoreAPI.BookStoreSteps;

import java.util.List;
import java.util.Map;
import java.util.Random;
public class APITC2_deleteBook {

    private AccountSteps accountSteps;
    private BookStoreSteps bookStoreSteps;
    private String userId;
    Map<String, String> book;

    @BeforeTest(alwaysRun = true)
    public void init() {
        accountSteps = new AccountSteps();
        bookStoreSteps = new BookStoreSteps();
        System.out.println("done");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        book = loginAndAddBook();
        System.out.println("book" +book);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        bookStoreSteps.deleteBooks(userId);
        
    }
    

    @Test(priority = 1, groups = "api")
    @Description("Test Case1 - Book added to collection")
    public void addBookTest() {
    	System.out.println("BaseAPITest42");
        Assert.assertTrue(findBookInCollection(book.get("isbn")), "Book not added to collection:");
        System.out.println("Book added to collection");
    }

    @Test(priority = 2, groups = "api")
    @Description("Test Case2 - Book deleted from collection")
    public void deleteBookTest() {
        Assert.assertEquals(bookStoreSteps.deleteBook(userId, book.get("isbn")), 204, "Book is absent in collection:");
        Assert.assertFalse(findBookInCollection(book.get("isbn")), "Book not deleted from collection:");
        System.out.println("Book deleted from collection");
    }


    private Map<String, String> loginAndAddBook() {
        userId = accountSteps.login().getUserId();
        System.out.println(userId);
        Assert.assertEquals(bookStoreSteps.deleteBooks(userId), 204, "Can't prepare collection:");
        List<Map<String, String>> booksList = bookStoreSteps.getBooks();
        Map<String, String> randomBook = booksList.get(new Random().nextInt(booksList.size()));
        System.out.println(randomBook);
        int responseCode =bookStoreSteps.addBook(userId, randomBook.get("isbn"));
        Assert.assertEquals(responseCode, 201, "Can't add book to collection:");
        return randomBook;
    }

    private boolean findBookInCollection(String isbn) {
        List<Map<String, String>> books = accountSteps.getUserData(userId).getBooks();
        System.out.println("books" + books);
        for (Map<String, String> item : books) {
            if (item.get("isbn").equals(isbn)) {
                return true;
            }
        }
        return false;
    }
}