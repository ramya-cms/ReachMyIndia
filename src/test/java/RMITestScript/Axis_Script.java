package RMITestScript;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Axis_CRM;
import POM_Classes.Login;


public class Axis_Script extends BaseTest{

	private Login loginPage;
	private static Axis_CRM axis;
	
	
	@Test(priority=1)
	 public  void AxisPage() throws InterruptedException, SQLException, IOException{
		driver = getDriver(); // Get the initialized WebDriver instance
       Thread.sleep(3000);
		
		String username = Excel.Testdata(Path, "Sheet1", 0, 0);
	    String password = Excel.Testdata(Path, "Sheet1", 0, 1);
	    
	    String Fname = Excel.Testdata(Path, "Sheet3", 3, 1);
	    String Lname = Excel.Testdata(Path, "Sheet3", 3, 2);
	    
	    String Mno = Excel.Testdata(Path, "Sheet3", 3, 3);
	    String pincode = Excel.Testdata(Path, "Sheet3", 3, 4);
	    
	    loginPage = new Login(driver);
        // Perform login
        loginPage.login(username, password);
        Thread.sleep(3000);
	    
        
        axis = new Axis_CRM(driver);
        axis.form(Fname, Lname, Mno, pincode);
        
	}
	
}
