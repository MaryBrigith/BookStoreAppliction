package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	
	 public WebDriver ldriver;

	public LoginPage(WebDriver ldriver) {
	        this.ldriver = ldriver;
	        PageFactory.initElements(ldriver, this);
	    }
	
	@FindBy(id="userName")
    @CacheLookup
    WebElement Username;

    @FindBy(id="password")
    @CacheLookup
    WebElement Password;

    @FindBy(id="login")
    @CacheLookup
    WebElement Login;

    @FindBy(id = "name")
    WebElement errorMsg;

    @FindBy(id = "submit")
    @CacheLookup
    WebElement Submit;
    
    public void setUserName(String username) {
        Username.sendKeys(username);
    }

    public void setPassword(String password) {
        Password.sendKeys(password);
    }

    public void clickLogin() {
    	//scrollIntoView(driver,Login);
    	JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].scrollIntoView();", Login);
        Login.click();
    }

    public String getErrorMsg() {
        return errorMsg.getText();
    }

    public void clickLogout() {
    	Submit.click();
    }

}
