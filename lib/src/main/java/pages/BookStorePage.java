package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookStorePage extends BasePage {
	

	public BookStorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	@FindBy(xpath = "//span[text()='Book Store']")
    @CacheLookup
    WebElement BookStore;
	
    @FindBy(id = "userName-value")
    @CacheLookup
    WebElement labelUserName;

    @FindBy(xpath = "//span[text()='Profile']")
    WebElement labelProfile;
    
    @FindBy(xpath = "//div[@class='rt-tbody'] //a")
    @CacheLookup
    List<WebElement> bookLinks;

    @FindBy(xpath = "//div[@class='rt-tbody'] //a")
    @CacheLookup
    List<WebElement> userBookLists;
    
    @FindBy(xpath = "//*[text()='Add To Your Collection']")
    @CacheLookup
    WebElement addNewRecordButton;

    @FindBy(xpath = "//*[text()='Back To Book Store']")
    @CacheLookup
    WebElement backButton;
    
    @FindBy(xpath = "//div[@class='rt-tbody'] //div[@class='action-buttons']/span[@id='delete-record-undefined']")
    @CacheLookup
    List<WebElement> deleteIcons;
    
    @FindBy(xpath = "//button[text()='Delete All Books']")
    @CacheLookup
    WebElement btnDeleteBooks;
    
    @FindBy(xpath = "//div[@class='modal-content']//button[@id='closeSmallModal-ok']")
    @CacheLookup
    WebElement alertOkButton;
    
    public void clickOnBookStore() {
    	scrollIntoView(driver,BookStore);
        BookStore.click();
    }

    public List<WebElement> getBooksOnStore() {
    	return bookLinks;
        
    }
    
    public String selectBook(int i) {
    	int randomNo =new Random().nextInt(bookLinks.size());
    	scrollIntoView(driver,bookLinks.get(randomNo));
    	String selectedBook = bookLinks.get(randomNo).getText();
    	bookLinks.get(randomNo).click();
        return selectedBook;
    }
    
    public String setLabelUserName() {
        return labelUserName.getText();
    }
    
    public void goToProfile() {
    	scrollIntoView(driver,labelProfile);
        labelProfile.click();
    }
    public List<String> getUserBookLists() {
    	List<String> lists = new ArrayList<String>();
        userBookLists.forEach((item -> lists.add(item.getText())));
        return lists;
    }

    public void addToCollection () {
        scrollIntoView(driver,addNewRecordButton);
        addNewRecordButton.click();
    }

    public void backToBookStore () {
        scrollIntoView(driver,backButton);
        backButton.click();
    }
    
    public void deleteBook(int i) {
        if (deleteIcons.get(i).isDisplayed())
        	deleteIcons.get(i).click();

        else if (!(deleteIcons.get(i).isDisplayed())) {
            scrollIntoView(driver,deleteIcons.get(i));
            deleteIcons.get(i).click();
        }
        
    }
    
    public void clickOK() {
    	scrollIntoView(driver,alertOkButton);
    	alertOkButton.click();
    }
    public void setBtnDeleteBooks() {
    	//scrollIntoView(driver,btnDeleteBooks);
        btnDeleteBooks.click();
    }


}
