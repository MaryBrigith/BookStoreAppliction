package bookStoreApplictionUI;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import pages.BookStorePage;
import pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pages.BasePage;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
@Story("Add book to collection")
public class UITC1_addBook extends BasePage {
	
	LoginPage loginPage;
	BookStorePage bookStorePage;
	List<WebElement> booksOnStore;
	List<String> bookLists;
	int randomNo;
	
	@Test(priority = 1) 
	@Step("Login")
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
            screenShot(driver,"login");
            Assert.assertTrue(false);
        }
        String userName = bookStorePage.setLabelUserName();
         if(username.equals(userName)) {
             Assert.assertTrue(true);
        }
        else {
            screenShot(driver,"login");
            Assert.assertTrue(false);
        }
         System.out.println("User looged in");
    }
	
	@Test(priority = 2)
	@Step("Add book")
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

}
