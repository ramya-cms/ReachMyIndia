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
