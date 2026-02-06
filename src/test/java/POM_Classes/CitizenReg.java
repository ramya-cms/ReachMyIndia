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

public class CitizenReg extends Base_Page
{

	WebDriver driver;
	WebDriverWait wait;
	
		@FindBy(xpath="//a[normalize-space()='Citizen Registration']")
		private WebElement citreg;
	
		@FindBy(xpath="//input[@id='MobileNumber']")
	    private WebElement mobileNumberInput;

	    @FindBy(xpath = "//input[@id='mobileotpsend']")
	    private WebElement mobileOtpButton;
	    
	    @FindBy(xpath = "//input[@id='MobileOTP']")
	    private WebElement mobileOtpInput;

	    @FindBy(xpath="//input[@id='EmailId']")
	    private WebElement emailInput;

	    @FindBy(xpath = "//input[@id='emailotpsend']")
	    private WebElement emailOtpButton;

	    @FindBy(xpath = "//input[@id='EmailOTP']")
	    private WebElement emailOtpInput;

	    @FindBy(xpath = "//input[@id='Continue']")
	    private WebElement continueButton;

	   
	    public CitizenReg(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        PageFactory.initElements(driver, this);
	    }

	    
	    public void reg()
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(citreg)).click();
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


