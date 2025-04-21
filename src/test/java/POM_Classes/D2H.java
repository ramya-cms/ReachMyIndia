package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class D2H extends Base_Page
{
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	    
	
	@FindBy(xpath="//h4[normalize-space()='Easypay']")
	private WebElement Easypay;
	
	@FindBy(xpath="//h4[normalize-space()='D2H Recharge']")
	private WebElement D2H;
	
	@FindBy(xpath="//div[@class='input-field']//input[@id='vCRMN']")
	private WebElement Mob;
	
	@FindBy(xpath="//input[@id='FetchPlanDetails']")
	private WebElement Fetch;
	
	@FindBy(xpath="//input[@id='Recharge_03005421705']")
	private WebElement Recharge;
	
	@FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement paynow;
	
	@FindBy(xpath="//a[@role='button']")
	private WebElement hover;
	
	@FindBy(xpath="//a[normalize-space()='Sign out']")
	private WebElement logout;
	
	public D2H(WebDriver driver)
	{
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
	}
	
	public void details(String mobile) throws InterruptedException
	{
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("arguments[0].click()", Easypay);
		
		 wait.until(ExpectedConditions.elementToBeClickable(Easypay)).click();
		
		 wait.until(ExpectedConditions.elementToBeClickable(D2H)).click();
		
		 wait.until(ExpectedConditions.visibilityOf(Mob)).sendKeys(mobile);
		
		 wait.until(ExpectedConditions.elementToBeClickable(Fetch)).click();
		
		 Thread.sleep(3000);
		
	     wait.until(ExpectedConditions.elementToBeClickable(Recharge)).click();
	     
	     Thread.sleep(2000);
	     
	   //  JavascriptExecutor js2 = (JavascriptExecutor) driver;
	   //  js2.executeScript("window.scrollTo(0,-250)");
	     
	     wait.until(ExpectedConditions.elementToBeClickable(hover)).click();
	     
	     wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
	     
		
	}

}
