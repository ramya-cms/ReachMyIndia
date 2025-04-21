package RMITestScript;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.D2H;
import POM_Classes.Login;

public class D2H_Script extends BaseTest
{

	private Login loginPage;
	private D2H dTh;

	@Test(priority=1)
	public void D2h() throws InterruptedException
	{
		driver = getDriver();
		Thread.sleep(3000);
		
		String username = Excel.Testdata(Path, "Sheet1", 0, 0);
	    String password = Excel.Testdata(Path, "Sheet1", 0, 1);
	    
	    loginPage = new Login(driver);
        // Perform login
        loginPage.login(username, password);
        Thread.sleep(3000);
        
        String mob = Excel.Testdata(Path, "Sheet4", 17, 2);
        
        dTh = new D2H(driver);
        dTh.details(mob);
		
	}
}
