package POM_Classes;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Generic.Base_Page;
import org.openqa.selenium.TimeoutException;  // Ensure correct import

public class Master_Payment extends Base_Page {
    private WebDriver driver;
    private WebDriverWait wait;
   

    // Page elements
    @FindBy(linkText ="Tests")
    private WebElement prev;


    @FindBy(xpath = "//a[@id='collapse_UNB']")
    private WebElement ibn;

    @FindBy(xpath = "//input[@id='btnSubmit']")
    private WebElement pay;

    @FindBy(xpath = "//input[@id='btnCancel']")
    private WebElement cancel;

    @FindBy(xpath = "//li[@id='net-banking-list-AXIB-pop']//img[@class='accrdn-arrw']")
    private WebElement axis;

    @FindBy(xpath = "//li[@id='net-banking-list-AXIB-pop']//button[@type='button'][normalize-space()='PROCEED']")
    private WebElement proceed;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement user;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement pswd;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement sub;

    @FindBy(xpath = "//input[@name='authenticate']")
    private WebElement success;

    @FindBy(xpath = "//input[@name='failure1']")
    private WebElement failure;

    @FindBy(xpath = "//small[normalize-space()='Back']")
    private WebElement back;

    @FindBy(xpath = "//button[@class='common-btn txt-btn']")
    private WebElement yes;

    @FindBy(xpath = "//a[@role='button']")
    private WebElement log;

    // Constructor
    public Master_Payment(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null!");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    } 

    // Method to perform the banking operations
    public void bank() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(ibn)).click();
        scrollAndClick(pay);
    }

    

    // Method to handle Axis Bank login and switch back to the original tab
    public void axis(String username, String password) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(axis)).click();
        wait.until(ExpectedConditions.elementToBeClickable(proceed)).click();

        // Enter credentials
        wait.until(ExpectedConditions.visibilityOf(user)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(pswd)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(sub)).click();

        // Handle success scenario
        wait.until(ExpectedConditions.elementToBeClickable(success)).click();
        
        Thread.sleep(4000);

        // Get the original window handle from MasterEmailTestPage (It is now set)
        String originalWindowHandle = MasterEmailTestPage.originalWindowHandle;

        if (originalWindowHandle != null) {
            driver.switchTo().window(originalWindowHandle);
            System.out.println("Switched back to the original tab.");
        } else {
            System.out.println("Original tab window handle not found.");
        }

        // Perform actions on the original tab (e.g., clicking a link)
        try {
            wait.until(ExpectedConditions.elementToBeClickable(prev)).click();
          
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for 'prev' button. Retrying with JavaScript...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", prev);
        }
        
        Thread.sleep(1000);
        // Refresh the page
        driver.navigate().refresh();
    }

    // Method to handle Axis Bank cancellation process
    public void axiscancel(String username, String password) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(axis)).click();
        wait.until(ExpectedConditions.elementToBeClickable(proceed)).click();
        wait.until(ExpectedConditions.visibilityOf(user)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(pswd)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(sub)).click();
        wait.until(ExpectedConditions.elementToBeClickable(failure)).click();
        wait.until(ExpectedConditions.elementToBeClickable(back)).click();
        wait.until(ExpectedConditions.elementToBeClickable(yes)).click();
    }

    // Helper method to handle scrolling and clicking
    private void scrollAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);
        }
    }
}
