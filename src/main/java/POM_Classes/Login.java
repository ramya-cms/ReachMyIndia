package POM_Classes;
import org.openqa.selenium.support.PageFactory;

import Generic.BaseTest;

public class Login extends BaseTest {
	
   
	public Login(){
		PageFactory.initElements(driver, this);
 
	}
	
}
