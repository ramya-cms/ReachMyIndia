package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class CitzForm extends Base_Page
{
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	@FindBy(xpath="//input[@id='FullName']")
	private WebElement fullname;
		
	@FindBy(xpath="//input[@id='LoginUserName']")
	private WebElement user;
	
	@FindBy(xpath="//input[@id='Password']")
	private WebElement pass;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	private WebElement conpass;
	
	@FindBy(xpath="//input[@id='btnRegistration']")
	private WebElement submit;
	
	
	public CitzForm(WebDriver driver)
	{
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
		
	}
	
	
	public void form(String name,String login,String pwd,String cpwd) throws InterruptedException
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.visibilityOf(fullname)).sendKeys(name);
		
		wait.until(ExpectedConditions.visibilityOf(user)).sendKeys(login);
		
		
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(pass)).sendKeys(pwd);
		
		wait.until(ExpectedConditions.visibilityOf(conpass)).sendKeys(cpwd);
		
		Thread.sleep(5000);
		
		js.executeScript("window.scrollBy(0,680)", "");
		
		wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
		
	}
	
	
	
}
