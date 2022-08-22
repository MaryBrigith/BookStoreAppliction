package pages;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.EventListener;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.io.Files;

import utilities.ReadConfig;

public class BasePage {
	
	ReadConfig readConfig = new ReadConfig();

    public String baseURL = readConfig.getBaseUrl();
    public String username = readConfig.getUsername();
    public String password = readConfig.getPassword();
    public String browser = readConfig.getBrowser();
    public WebDriver driver;
    
    @BeforeClass
    public  void launchDriver() {
        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
            driver = new ChromeDriver();
        }

        if(browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }

        if(browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver",readConfig.getEdgePath());
            driver = new EdgeDriver();
            }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.get(baseURL);
    }
    
    public void scrollIntoView(WebDriver driver, WebElement element) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
    
    public String screenShot(WebDriver driver, String filename) throws IOException {
        TakesScreenshot ts =  (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dst = new File("../Screenshots/"+filename+".png");
        Files.copy(src,dst);
        System.out.println("Screenshot Taken");
        return filename;
    }
    public boolean isAlertPresent(WebDriver webdriver) {
    	if(ExpectedConditions.alertIsPresent() == null) {
    		return false;
    	}
		return true;
        
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
