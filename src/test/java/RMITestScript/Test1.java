package RMITestScript;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import POM_Classes.Login;

public class Test1 extends BaseTest {
	Login login;
	
	
	 @BeforeTest
	 public void setup() 
	 { 
	 intialize();
	 //changes
		
	 }
	@Test
	  public void loginPage()
	{
		  login=new Login();
		
}
}