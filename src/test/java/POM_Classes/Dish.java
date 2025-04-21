package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class Dish extends Base_Page

{
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	    
	
	@FindBy(xpath="//h4[normalize-space()='Easypay']")
	private WebElement Easypay;
	
	@FindBy(xpath="//div[@id='myServices']//div[1]//a[1]//div[1]//div[1]")
	private WebElement Dish;
	
	@FindBy(xpath="//div[@class='input-field']//input[@id='vCRMN']")
	private WebElement Mob;
	
	@FindBy(xpath="//input[@id='FetchPlanDetails']")
	private WebElement Fetch;
	
	@FindBy(xpath="//input[@id='Recharge_02805016795']")
	private WebElement Recharge;
	
	@FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement paynow;
    
	
	
	public Dish(WebDriver driver)
	{
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
		
	}
	

	public void dist_tv(String mobile) throws InterruptedException
	{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollBy(0,980)", "");
		
		 wait.until(ExpectedConditions.elementToBeClickable(Easypay)).click();
		
		 wait.until(ExpectedConditions.elementToBeClickable(Dish)).click();
		
		 wait.until(ExpectedConditions.visibilityOf(Mob)).sendKeys(mobile);
		
		 wait.until(ExpectedConditions.elementToBeClickable(Fetch)).click();
		
		 Thread.sleep(3000);
		
	     wait.until(ExpectedConditions.elementToBeClickable(Recharge)).click();
	     
	     Thread.sleep(2000);
	     
	     JavascriptExecutor js1 = (JavascriptExecutor) driver;
	     js1.executeScript("window.scrollBy(0,1000)", "");
	     
	     wait.until(ExpectedConditions.elementToBeClickable(paynow)).click();
	     
	     Thread.sleep(6000);
		
	}
	
	/* public void payment()
   	 {
   		 scrollToElement(paynow);
   		 
   		 clickElement(paynow,"Pay Now");
   	 }*/
	 
	 // Scroll and click with enhanced handling
	 private void scrollToElement(WebElement element) 
	 {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
	 }
    
	 private void clickElement(WebElement element, String elementName) 
	 {
        try {
            // Wait for the element to be clickable and click
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println(elementName + " is intercepted. Clicking via JavaScript.");
            js.executeScript("arguments[0].click();", element);
        } catch (TimeoutException e) {
            throw new RuntimeException(elementName + " is not clickable within the timeout period.", e);
        }
    }
	
}
