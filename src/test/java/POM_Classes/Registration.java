package POM_Classes;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class Registration extends Base_Page {
    
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "body > inout:nth-child(1) > section:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1)")
    private WebElement regBtn;

    @FindBy(id = "IsAccept")
    private WebElement checkBox;

    @FindBy(id = "btnRegister")
    private WebElement registerButton;

    @FindBy(how = How.ID, using = "MobileNumber")
    private WebElement mobileNumberInput;

    @FindBy(id = "mobileotpsend")
    private WebElement mobileOtpButton;
    
    @FindBy(id = "MobileOTP")
    private WebElement mobileOtpInput;

    @FindBy(how = How.ID, using = "EmailId")
    private WebElement emailInput;

    @FindBy(id = "emailotpsend")
    private WebElement emailOtpButton;

    @FindBy(id = "EmailOTP")
    private WebElement emailOtpInput;

    @FindBy(id = "Continue")
    private WebElement continueButton;

    public Registration(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void initiateRegistration() throws InterruptedException {
        clickElement(regBtn);
        clickElement(checkBox);
        clickElement(registerButton);
    }

    public void enterMobileNumber(String mobileNumber) throws InterruptedException {
        sendKeysToElement(mobileNumberInput, mobileNumber);
        clickElement(mobileOtpButton);
        Thread.sleep(15000);
    }

    public void enterMobileOtp(String mOtp) {
        try {
            waitUntilElementClickable(mobileOtpInput, 30);
            scrollToElement(mobileOtpInput);
            System.out.println("Entering Mobile OTP: " + mOtp); // Debug line
            mobileOtpInput.clear(); // Clear before sending new OTP
            mobileOtpInput.sendKeys(mOtp);
        } catch (Exception e) {
            System.out.println("Failed to enter mobile OTP: " + e.getMessage());
        }
    }

    public void enterEmailId(String emailId) throws InterruptedException {
        sendKeysToElement(emailInput, emailId);
        clickElement(emailOtpButton);
        Thread.sleep(15000);
    }

    public void enterEmailOtp(String eOtp) {
        try {
            waitUntilElementClickable(emailOtpInput, 30);
            scrollToElement(emailOtpInput);
            System.out.println("Entering Email OTP: " + eOtp); // Debug line
            emailOtpInput.clear(); // Clear before sending new OTP
            emailOtpInput.sendKeys(eOtp);
        } catch (Exception e) {
            System.out.println("Failed to enter email OTP: " + e.getMessage());
        }
    }

    public void completeRegistration() throws InterruptedException {
        clickElement(continueButton);
    }

    private void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            scrollToElement(element);
            System.out.println("Clicking element: " + element.toString()); // Debug line
            element.click();
        } catch (Exception e) {
            System.out.println("Failed to click element: " + e.getMessage());
        }
    }

    private void sendKeysToElement(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            scrollToElement(element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Failed to send keys to element: " + e.getMessage());
        }
    }

    private void waitUntilElementClickable(WebElement element, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
            .until(ExpectedConditions.elementToBeClickable(element));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500); // Adjust based on your application's behavior
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
