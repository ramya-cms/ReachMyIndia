package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.BaseTest;

public class Axis_CRM extends BaseTest{
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//h4[normalize-space()='AXIS CRM Lead Generation']")
	private WebElement axis;
	
	@FindBy(xpath="//input[@id='FirstName']")
	private WebElement fname;
	
	@FindBy(xpath="//input[@id='LastName']")
	private WebElement lname;
	
	@FindBy(xpath="//input[@id='MobileNo']")
	private WebElement Mobno;
	
	@FindBy(xpath="//select[@id='State']")
	private WebElement State;
	
	@FindBy(xpath="//select[@id='District']")
	private WebElement District;
	
	
	@FindBy(xpath="//select[@id='Branch']")
	private WebElement Branch;
	
	
	@FindBy(xpath="//select[@id='Product']")
	private WebElement Product;
	
	@FindBy(xpath="//select[@id='SubProduct']")
	private WebElement subProduct;
	
	@FindBy(xpath="//input[@id='PinCode']")
	private WebElement Pincode;
	
	
	@FindBy(xpath="//input[@id='Continue']")
	private WebElement cont;
	
	public Axis_CRM(WebDriver driver) 
	{
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
	}
	
	public void form(String fstname,String lstname,String mnum,String pin) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,950)", "");
	        
	     // Scroll the submit button into view
	        JavascriptExecutor js1 = (JavascriptExecutor) driver;
	        js1.executeScript("arguments[0].scrollIntoView(true);", axis);
	        Thread.sleep(1000); // Just to ensure the scroll has completed

	        // Try clicking the submit button
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(axis)).click();
	        } catch (ElementClickInterceptedException e) {
	            // Use JavaScript click as a fallback
	            js1.executeScript("arguments[0].click();", axis);
	        }


		 
		wait.until(ExpectedConditions.visibilityOf(fname)).sendKeys(fstname);

		wait.until(ExpectedConditions.visibilityOf(lname)).sendKeys(lstname);
		
		wait.until(ExpectedConditions.visibilityOf(Mobno)).sendKeys(mnum);
		
		Select sel = new Select(State);
	    sel.selectByIndex(1);
	    
		Thread.sleep(2000);
		
	    Select sel2 = new Select(District);
	    sel2.selectByIndex(1);
	    
		Thread.sleep(2000);
	    
	    Select sel3 = new Select(Branch);
	    sel3.selectByIndex(1);
	    
		Thread.sleep(2000);
		
	    Select sel4 = new Select(Product);
	    sel4.selectByIndex(1);
	    
		Thread.sleep(2000);
		
	    Select sel5 = new Select(subProduct);
	    sel5.selectByIndex(1);
	    
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(Pincode)).sendKeys(pin);
		
		
		JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].scrollIntoView(true);", cont);
        
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
        } catch (ElementClickInterceptedException e) {
            // Use JavaScript click as a fallback
            js2.executeScript("arguments[0].click();", cont);
        }
		//wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
	}
	
	
}