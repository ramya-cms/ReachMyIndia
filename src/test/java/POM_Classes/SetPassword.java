package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class SetPassword extends Base_Page
{

	 WebDriver driver;
	 WebDriverWait wait;

	    @FindBy(xpath="//input[@id='Password']")
	    private WebElement npwd;
	    
	    @FindBy(xpath="//input[@id='Repassword']")
	    private WebElement cpwd;
	
	    @FindBy(xpath="//input[@id='Update']")
	    private WebElement submit;
	    
	    public SetPassword(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	    }
	    
	    public void password(String npswd,String conpswd)
	    {
	    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    	wait.until(ExpectedConditions.visibilityOf(npwd)).sendKeys(npswd);
	    	wait.until(ExpectedConditions.visibilityOf(cpwd)).sendKeys(conpswd);
	    	wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
	    }
}