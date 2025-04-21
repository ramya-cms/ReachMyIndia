package RMITestScript;

import java.io.IOException;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Axis_CRM;
import POM_Classes.DDE;
import POM_Classes.Login;

public class DDE_Script extends BaseTest
{	
	
	private Login loginPage;
	private  DDE dde;
	
	@Test(priority=1)
	public void doc() throws InterruptedException, IOException
	{
		
			driver = getDriver(); // Get the initialized WebDriver instance
			Thread.sleep(3000);
			
			String username = Excel.Testdata(Path, "Sheet1", 0, 0);
		    String password = Excel.Testdata(Path, "Sheet1", 0, 1);
		    
		    loginPage = new Login(driver);
	        // Perform login
	        loginPage.login(username, password);
	        Thread.sleep(3000);
		    
	        String docn = Excel.Testdata(Path, "Sheet4", 8, 0);
	        String path = Excel.Testdata(Path, "Sheet4", 8, 1);
	        String state = Excel.Testdata(Path, "Sheet4", 8, 2);
	        String fname = Excel.Testdata(Path, "Sheet4", 8, 3);
	        String sname = Excel.Testdata(Path, "Sheet4", 8, 4);
	        String article = Excel.Testdata(Path, "Sheet4", 8, 6);
	        String cprice = Excel.Testdata(Path, "Sheet4", 8, 5);
	        
	      
	        String mob = Excel.Testdata(Path, "Sheet4", 8, 7);
	        String dtype = Excel.Testdata(Path, "Sheet4", 8, 8);
	        String docid = Excel.Testdata(Path, "Sheet4", 8, 9);
	        String dob = Excel.Testdata(Path, "Sheet4", 8, 10);
	        
	        dde = new DDE(driver);
	        dde.details(docn, path, state, fname, sname, article ,cprice);
	        dde.signatory(mob, dtype, docid, dob);
	        dde.otherdetails();
	        dde.payment();
		
	}

}
