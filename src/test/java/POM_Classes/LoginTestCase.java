package POM_Classes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;
import io.netty.handler.timeout.TimeoutException;

public class LoginTestCase extends Base_Page {
     WebDriver driver;
     WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(LoginTestCase.class);

    @FindBy(id = "UserName")
    private WebElement usrname;

    @FindBy(id = "Password")
    private WebElement passwd;
    
    @FindBy(id="dntCaptchaImg")
    private WebElement captchaElementId;

    @FindBy(xpath = "//span[normalize-space()='Login']")
    private WebElement login;
    
    @FindBy(xpath="//a[@role='button']")
    WebElement drop;
    
    @FindBy(xpath="//a[normalize-space()='Sign out']")
    WebElement logout;
    
    @FindBy(xpath = "//div[contains(text(),'Invalid Credentials')]")  // Ensure this selector is correct
    private WebElement errorMessage;
    
    @FindBy(css="div[class='text-center col-sm-12'] div")
    private WebElement errorMessage1;
    
    @FindBy(xpath="//div[@id='UserName-invalid']")
    private WebElement invaliduser;
    
    public LoginTestCase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Ensure page is fully loaded
       // wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

        wait.until(ExpectedConditions.visibilityOf(usrname)).sendKeys(username);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOf(passwd)).sendKeys(password);
        //Thread.sleep(5000);
        try {
             wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dntCaptchaImg")));
            // CAPTCHA is present, now wait for user input
            Thread.sleep(5000); // Time for manual CAPTCHA entry
        } catch (TimeoutException e) {
            log.debug("CAPTCHA not present, continuing without waiting.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(login)).click();
    }
    
    public void logout() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(drop)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
        // Wait for the login page or any element that signifies successful logout
        wait.until(ExpectedConditions.urlContains("Login"));
    }
    
    public List<WebElement> getErrorMessages() {
        log.debug("Checking for error message elements");
        List<WebElement> errorElements = new ArrayList<>();
        
        // List of possible error message locators
        List<By> errorLocators = List.of(
            By.xpath("//div[contains(text(),'Invalid Credentials')]"),
            By.xpath("//div[contains(text(),'username or password invalid')]")
        );
        
        for (By locator : errorLocators) {
            try {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            log.debug("Error message found: " + element.getText().trim());
                            errorElements.add(element);
                        } else {
                            log.debug("Found element but it is not displayed: " + locator);
                        }
                    }
                } else {
                    log.debug("No elements found for locator: " + locator);
                }
            } catch (Exception e) {
                log.debug("Exception while locating elements with locator: " + locator, e);
            }
        }
        
        if (errorElements.isEmpty()) {
            log.debug("No error message elements found or not displayed");
        }
        
        return errorElements;
    }
    
    

//    public List<WebElement> getErrorMessages() {
//        log.debug("Checking for error message elements");
//        List<WebElement> errorElements = new ArrayList<>();
//        
//        try {
//            // Wait for error messages to be visible
//            if (wait.until(ExpectedConditions.visibilityOf(errorMessage)) != null) {
//                log.debug("Error message found: " + errorMessage.getText().trim());
//                errorElements.add(errorMessage);
//            }
//            
//            if (wait.until(ExpectedConditions.visibilityOf(errorMessage1)) != null) {
//                log.debug("Error message found: " + errorMessage1.getText().trim());
//                errorElements.add(errorMessage1);
//            }
//            
//            if (wait.until(ExpectedConditions.visibilityOf(invaliduser)) != null) {
//                log.debug("Error message found: " + invaliduser.getText().trim());
//                errorElements.add(invaliduser);
//            }
//            if (errorElements.isEmpty()) {
//                log.debug("No error message elements found or not displayed");
//            }
//            
//        } catch (Exception e) {
//            log.debug("Error while retrieving the error message elements", e);
//        }
//        
//        return errorElements;
//    }
}



