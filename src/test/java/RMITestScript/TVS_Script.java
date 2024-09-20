package RMITestScript;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Base_Page;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.Registration;
import POM_Classes.TVS_Service;

public class TVS_Script extends BaseTest
{
	
	 //private WebDriver driver;
	   private Login loginPage;
	private static TVS_Service tser;
	
/*	@BeforeTest
	public  void setup() throws IOException, InterruptedException
	{	
		
		initialize();
		//TakeScreenShot ts=new TakeScreenShot(driver);
		//driver.close();
		
	}
	*/
	@Test(priority=1)
	 public  void TVSPage() throws InterruptedException, SQLException, IOException{
		driver = getDriver(); // Get the initialized WebDriver instance
        Thread.sleep(3000);
		
		String username = Excel.Testdata(Path, "Sheet1", 0, 0);
	    String password = Excel.Testdata(Path, "Sheet1", 0, 1);
	    
	    String Fname = Excel.Testdata(Path, "Sheet1", 12, 0);
	    String Phone = Excel.Testdata(Path, "Sheet1", 12, 1);
	    String Mobile = Excel.Testdata(Path, "Sheet1", 12, 2);
	    String Add1 = Excel.Testdata(Path, "Sheet1", 12, 3);
	    String Pin = Excel.Testdata(Path, "Sheet1", 12, 4);
	    
	    
	   
		 loginPage = new Login(driver);
         // Perform login
         loginPage.login(username, password);
         Thread.sleep(3000);
	    
	     Thread.sleep(1000);
	     tser = new TVS_Service(driver);
	     Thread.sleep(1000);
	     
	     Thread.sleep(1000);
	     tser.details(Fname, Phone, Mobile, Add1, Pin, Pin, Pin, Pin);
}

}
