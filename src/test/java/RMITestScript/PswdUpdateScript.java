package RMITestScript;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.CredentailsManager;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.SetPassword;

public class PswdUpdateScript extends BaseTest
{
	 private Login loginPage;
	 private SetPassword spwd;
	 
	 
	@Test(priority=7) 
	public void logincred() throws InterruptedException
	{
		driver = getDriver(); // Get the initialized WebDriver instance
        Thread.sleep(3000);
      
		 String username = CredentailsManager.getUsername();
		 String password =  Excel.Testdata(Path, "Sheet1", 0, 1);
		 
		 String npassword = Excel.Testdata(Path, "Sheet1", 0, 1);
		 String conpassword = Excel.Testdata(Path, "Sheet1", 0, 1);
		 
		 
		 spwd = new SetPassword(driver);
		 spwd.password(npassword, conpassword);
		 
		 Thread.sleep(2000);
		 
		 driver.navigate().refresh();
		 
		// loginPage = new Login(driver);
	     Thread.sleep(2000);
	        
	     // Perform login
	     loginPage.login(username, password);
	     Thread.sleep(3000);
	}
}
