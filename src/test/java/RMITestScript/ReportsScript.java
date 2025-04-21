package RMITestScript;


import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.D2H;
import POM_Classes.Login;
import POM_Classes.Reports;


public class ReportsScript extends BaseTest
{

	private Login loginPage;
	private Reports repor;

	@Test(priority=1)
	public void Repo() throws InterruptedException
	{
		driver = getDriver();
		Thread.sleep(3000);
		
		String username = Excel.Testdata(Path, "Sheet1", 0, 0);
	    String password = Excel.Testdata(Path, "Sheet1", 0, 1);
	    
	    loginPage = new Login(driver);
        // Perform login
        loginPage.login(username, password);
        Thread.sleep(3000);
	
        String dep = Excel.Testdata(Path, "Sheet4", 16, 5);
        String rtyp = Excel.Testdata(Path, "Sheet4", 16, 6);
        
        repor = new Reports(driver);
        repor.tranDetails(dep,rtyp);
	
	}
	
	
	
	
	
	
}
