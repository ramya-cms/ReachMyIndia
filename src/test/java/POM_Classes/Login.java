package POM_Classes;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Generic.Base_Page;

public class Login extends Base_Page {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@class='loginTop']")
    private WebElement login;

    @FindBy(id = "UserName")
    private WebElement usrname;

    @FindBy(id = "Password")
    private WebElement passwd;

    @FindBy(xpath = "//span[normalize-space()='Login']")
    private WebElement log;
    
    @FindBy(xpath="//a[@role='button']")
    WebElement drop;
    
    @FindBy(xpath="//a[normalize-space()='Sign out']")
    WebElement logout;
    

    
    public Login(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Ensure page is fully loaded
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));

        // Scroll to and click login button with retry mechanism
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 3) {
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", login);
                wait.until(ExpectedConditions.elementToBeClickable(login)).click();
                clicked = true;
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click intercepted, retrying... Attempt: " + (attempts + 1));
                Thread.sleep(1000); // Add a brief pause before retrying
                attempts++;
            }
        }

        // Check if click was successful
        if (!clicked) {
            throw new RuntimeException("Failed to click login button after multiple attempts");
        }

        wait.until(ExpectedConditions.visibilityOf(usrname)).sendKeys(username);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(passwd)).sendKeys(password);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(log)).click();
    }


    
//    public void logout()
//    {	
//    	wait.until(ExpectedConditions.elementToBeClickable(drop)).click();
//    	wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
//    }
    
   
}
