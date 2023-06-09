package POM_Classes;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic.Auto_Constant;
import Generic.BaseTest;
import Generic.Base_Page;

public class Login extends Base_Page
{
	 WebDriver driver;
	
	  @FindBy(xpath="//a[@class='loginTop']")
	  private WebElement login;
	  
	  @FindBy(id="UserName")
	  private WebElement usrname;
	  
	  @FindBy(id="Password")
	  private WebElement passwd;
	  
	  
	  @FindBy(xpath="//span[normalize-space()='Login']")
	  private WebElement button;
	 
 
	  public Login(WebDriver driver) 
		{
		  	this.driver=driver;
			PageFactory.initElements(driver, this);
		}

	 

	public void login(String username,String password) throws InterruptedException 
	 {
		Thread.sleep(2000);
		  login.click();
		  usrname.sendKeys(username);
		  Thread.sleep(2000);
		  passwd.sendKeys(password);
		  Thread.sleep(3000);
		  button.click();
		 
	}
	
}
