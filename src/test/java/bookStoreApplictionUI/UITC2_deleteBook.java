package bookStoreApplictionUI;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.BasePage;
import pages.BookStorePage;
import pages.LoginPage;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
@Story("Add and delete book")
public class UITC2_deleteBook extends BasePage{
	
	LoginPage loginPage;
	BookStorePage bookStorePage;
	List<WebElement> booksOnStore;
	List<String> bookLists;
	int randomNo;
	
	@Test(priority = 1)  
	@Step("User Login")
    @Description("User Login")
    public void loginTest() throws IOException {
        loginPage = new LoginPage(driver);
        bookStorePage = new BookStorePage(driver);
        loginPage.setUserName(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        if(driver.getTitle().equals("ToolsQA")) {
            Assert.assertTrue(true);
        }
        else {
            screenShot(driver,"loginTest");
            Assert.assertTrue(false);
        }
        String userName = bookStorePage.setLabelUserName();
         if(username.equals(userName)) {
             Assert.assertTrue(true);
        }
        else {
            screenShot(driver,"loginTest");
            Assert.assertTrue(false);
        }
        //lp.clickLogout();
    }
	
	@Test(priority = 2)
	@Step("Add book to collection")
    @Description("Add book to collection")

    public void bookStore() throws IOException, InterruptedException {
                
        bookStorePage.clickOnBookStore();
        booksOnStore = bookStorePage.getBooksOnStore();
        bookLists = new ArrayList<String>();
        booksOnStore.forEach((item -> bookLists.add(item.getText())));
    	randomNo =new Random().nextInt(booksOnStore.size());
    	String selectedBook = bookStorePage.selectBook(randomNo);
    	bookStorePage.addToCollection();
    	Thread.sleep(3000);
        if(isAlertPresent(driver)) {
         driver.switchTo().alert().accept();
        }
        
        System.out.println("Added Book " +selectedBook);
        bookStorePage.backToBookStore();
        bookStorePage.goToProfile();
        System.out.println(bookStorePage.getUserBookLists());
        Assert.assertTrue(bookStorePage.getUserBookLists().contains(selectedBook),"Book availble in collection");
    }
	
	@Test(priority=3)
	@Step("Delete book from collection ")
    @Description("Delete book from collection")

    public void profile() throws IOException, InterruptedException {

        //Viewing Profile
		List<String> bookList = bookStorePage.getUserBookLists();
		if(!bookList.isEmpty()){
			String book = bookStorePage.getUserBookLists().get(0);
			bookStorePage.deleteBook(0);
			bookStorePage.clickOK();
			acceptAlert();
			Assert.assertTrue(!bookStorePage.getUserBookLists().contains(book),"Book not availble in collection");
			System.out.println("Book deleted from collection");
		}
		
    }

}
