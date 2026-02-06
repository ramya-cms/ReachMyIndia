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

public class Solar_CRM extends BaseTest
{	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//div[9]//a[1]//div[1]//div[1]")
	private WebElement sol;
	
	@FindBy(xpath="//select[@id='City']")
	private WebElement city;
	
	@FindBy(xpath="//input[@id='CustomerName']")
	private WebElement cname;
	
	@FindBy(xpath="//input[@id='ContactNumber']")
	private WebElement cnumber;
	
	@FindBy(xpath="//input[@id='MonthlyAvgBill']")
	private WebElement monavgbil;
	
	@FindBy(xpath="//textarea[@id='Address']")
	private WebElement addr;
	
	@FindBy(xpath="//textarea[@id='AnySpecificRequirement']")
	private WebElement req;
	
	@FindBy(xpath="//button[@title='Maximum Size: 5 MB and Supported File Type: Pdf,Jpg,Png,jpeg']")
	private WebElement upload;
	
	@FindBy(xpath="//input[@id='Continue']")
	private WebElement cont;
	
	public Solar_CRM(WebDriver driver) 
	{
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
	}
	
	public void solar(String cunam,String num,String bil,String add,String requ,String upl) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,950)", "");
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("arguments[0].scrollIntoView(true);",sol);
		
	     try {
	            wait.until(ExpectedConditions.elementToBeClickable(sol)).click();
	        } catch (ElementClickInterceptedException e) {
	            // Use JavaScript click as a fallback
	            js1.executeScript("arguments[0].click();", sol);
	        }
	     
	     
	    Select sel = new Select(city);
	    sel.selectByIndex(1);
	    
	    wait.until(ExpectedConditions.visibilityOf(cname)).sendKeys(cunam);
	    wait.until(ExpectedConditions.visibilityOf(cnumber)).sendKeys(num);
	    wait.until(ExpectedConditions.visibilityOf(monavgbil)).sendKeys(bil);
	    wait.until(ExpectedConditions.visibilityOf(addr)).sendKeys(add);
	    wait.until(ExpectedConditions.visibilityOf(req)).sendKeys(requ);
	    wait.until(ExpectedConditions.visibilityOf(upload)).sendKeys(upl);
	    Thread.sleep(6000);
	    
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
        } catch (ElementClickInterceptedException e) {
            // Use JavaScript click as a fallback
            js.executeScript("arguments[0].click();", cont);
        }
	}
	

}
