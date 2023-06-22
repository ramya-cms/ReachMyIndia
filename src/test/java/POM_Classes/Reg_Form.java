package POM_Classes;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;

import org.apache.poi.hpsf.Date;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class Reg_Form extends Base_Page
{
	//static WebDriver driver;
   // WebDriverWait wait;
    
	@FindBy(id="ApplicantName")
	private WebElement appName;
	
	
	@FindBy(id="PanCardNumber")
	private WebElement Pan;
	
	@FindBy(xpath="//input[@id='DateOfBirth']")
	private WebElement dob;
	
	@FindBy(xpath="//td[@class='day weekend'][normalize-space()='2']")
	private WebElement date;
	
	@FindBy(xpath="//select[@id='Gender']")
	private WebElement gen;
	
	
	@FindBy(xpath="//select[@id='State']")
	private WebElement state;
	
	@FindBy(xpath="//select[@id='District']")
	private WebElement dist;
	
	
	@FindBy(xpath="//input[@id='CommunicationAddress1']")
	private WebElement pradr;
	
	@FindBy(xpath="//input[@id='CommunicationAddressPinCode']")
	private WebElement pincode;
	
	
	@FindBy(xpath="//input[@id='IsAddressSame']")
	private WebElement spaddr;
	
	
	@FindBy(xpath="//input[@id='IsAccept']")
	private WebElement cond;
	
	@FindBy(xpath="//input[@id='btnRegistration']")
	private WebElement cont;
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	public Reg_Form(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void details(String aname,String pan,String paddr,String pincde) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(appName)).sendKeys(aname);

		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(Pan)).sendKeys(pan);

		
		dob.click();
		
		date.click();
		
		
		
		Select sel = new Select(gen);
		sel.selectByIndex(1);
		
		Select sel1 = new Select(state);
		sel1.selectByVisibleText("Karnataka");
		
		Select sel2 = new Select(dist);
		sel2.selectByIndex(1);
		
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pradr)).sendKeys(paddr);
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pincode)).sendKeys(pincde);
		
	
		JavascriptExecutor js2 = (JavascriptExecutor)driver;
		js2.executeScript("window.scrollBy(0,350)","");
		
		js.executeScript("arguments[0].click();", spaddr);
		
		js.executeScript("arguments[0].click();", cond);
		
		new WebDriverWait(driver,Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(cont));
		
	}
}
