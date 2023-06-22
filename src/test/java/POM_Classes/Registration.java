package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class Registration extends Base_Page {
	
//	private WebDriver driver;
//    WebDriverWait wait; 
	 
	@FindBy(css=".signUpTop")
	private WebElement regbtn;
	
	@FindBy(id="IsAccept")
	private WebElement ckbox;
	
	@FindBy(id="btnRegister")
	private WebElement Rbutton;
	
	@FindBy(how=How.ID,using="MobileNumber")
	private static WebElement entrMovbileNum;
	
	@FindBy(id="mobileotpsend")
	private WebElement MOtpbtn;
	
	@FindBy(id="MobileOTP")
	private WebElement MOtp;
	
	@FindBy(how=How.ID,using="EmailId")
	private static WebElement entrEmlId;
	
	@FindBy(id="emailotpsend")
	private static WebElement emlOtpbtn;
	
	@FindBy(id="EmailOTP")
	private static WebElement EOtp;
	
	@FindBy(id="Continue")
	private static WebElement ctn;

	  public Registration(WebDriver driver) 
			{
			  	this.driver=driver;
				PageFactory.initElements(driver, this);
				
	}
	public void Registration_page(String MobNum, String EmlID) throws InterruptedException {
	
		regbtn.click();
		ckbox.click();
		Rbutton.click();
		Thread.sleep(3000);
		entrMovbileNum.sendKeys(MobNum);
		MOtpbtn.click();
		Thread.sleep(12000);
		entrEmlId.sendKeys(EmlID);
		emlOtpbtn.click();
		Thread.sleep(12000);
		ctn.click();
		
		/*new WebDriverWait(driver,Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(entrMovbileNum)).sendKeys(MobNum);
		MOtpbtn.click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Thread.sleep(10000);
		
		//new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(MOtp)).click();
		new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(entrEmlId)).sendKeys(EmlID);
		emlOtpbtn.click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Thread.sleep(10000);
		//new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(EOtp)).click();
		ctn.click();*/
		
		
	}
	
}
