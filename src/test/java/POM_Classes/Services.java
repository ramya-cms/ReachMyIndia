package POM_Classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic.BaseTest;

public class Services extends BaseTest {
	
	WebDriver driver;
		
	  @FindBy(xpath="//h4[@class='page-title']")
	  private WebElement services;
	  
	  @FindBy(xpath="//div[5]//a[1]//div[1]//div[1]")
	  private WebElement tvs;
	  
	  @FindBy(xpath="//input[@id='FullName']")
	  private WebElement fname;
	  
	  @FindBy(xpath="")
	  private WebElement pno;
	  
	  @FindBy(xpath="")
	  private WebElement mno;
	  
	  @FindBy(xpath="")
	  private WebElement address1;
	  
	  @FindBy(xpath="//input[@id='PinCode']")
	  private WebElement pincode;
	  
	  public Services(WebDriver driver) 
		{
		  	this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	  
	  public WebElement Serv() 
	  {
			return services;
	  }
}
