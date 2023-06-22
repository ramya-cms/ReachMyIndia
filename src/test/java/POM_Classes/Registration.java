package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class Registration extends Base_Page {
	
	private WebDriver driver;
    WebDriverWait wait; 
	 
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
			    wait = new WebDriverWait(driver, Duration.ofSeconds(50));		
			    PageFactory.initElements(driver, this);
				
	}
	public void Registration_page(String MobNum, String EmlID) throws InterruptedException {
	
		/*regbtn.click();
		ckbox.click();
		Rbutton.click();
		wait.until(ExpectedConditions.visibilityOf(entrMovbileNum)).sendKeys(MobNum);
		MOtpbtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(MOtp));
		//new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(MOtp)).click();
		//wait.until(ExpectedConditions.visibilityOf(entrMovbileNum)).sendKeys(EmlID);
		new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(entrEmlId)).sendKeys(EmlID);
		 emlOtpbtn.click();
		 WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(50));
	     wait1.until(ExpectedConditions.elementToBeClickable(EOtp));
		//new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.visibilityOf(EOtp)).click();
		ctn.click();*/
		
		regbtn.click();
		ckbox.click();
		Rbutton.click();
		new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOf(entrMovbileNum)).sendKeys(MobNum);
		MOtpbtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));
	    wait.until(ExpectedConditions.elementToBeClickable(MOtp));
	    new WebDriverWait(driver, Duration.ofMinutes(2)).until(ExpectedConditions.visibilityOf(entrEmlId)).sendKeys(EmlID);
	    emlOtpbtn.click();
	    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMinutes(2));
	    wait1.until(ExpectedConditions.elementToBeClickable(EOtp));
	    ctn.click();
	}
	
}
