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
	    String Dob = Excel.Testdata(Path, "Sheet1" , 12, 6);
	    String Add1 = Excel.Testdata(Path, "Sheet1", 12, 3);
	    String Pin = Excel.Testdata(Path, "Sheet1", 12, 4);
	    String path = Excel.Testdata(Path, "Sheet1", 12, 5);
	    String state = Excel.Testdata(Path, "Sheet1", 12, 7);
	    
	    String dist = Excel.Testdata(Path, "Sheet1", 12, 8);
	    
	    String tal = Excel.Testdata(Path, "Sheet1", 12, 9);
	    
	    String prod = Excel.Testdata(Path, "Sheet1", 12, 10);
	    String atrack = Excel.Testdata(Path, "Sheet1", 12, 12);
	    String dtype = Excel.Testdata(Path, "Sheet1", 12, 5);
	    
	    
	   
		 loginPage = new Login(driver);
         // Perform login
         loginPage.login(username, password);
         Thread.sleep(3000);
	  
	     tser = new TVS_Service(driver);
	     Thread.sleep(1000);
	     
	     tser.details(Fname, Phone, Mobile, Dob, Add1, state, dist, tal, Pin, prod, atrack, dtype, path);
	     tser.logout();
	     
	     
}
	@Test(priority=2)
	public void BlankFields()
	{
		
	}
	

}
