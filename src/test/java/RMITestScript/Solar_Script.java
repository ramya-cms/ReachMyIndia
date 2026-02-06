package RMITestScript;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.Solar_CRM;

public class Solar_Script extends BaseTest
{

	private Login loginPage;
	private static  Solar_CRM solPage;
	
	
	@Test(priority=1)
	public void solarPage() throws InterruptedException
	{
		driver = getDriver();
		Thread.sleep(3000);
		
		String username = Excel.Testdata(Path, "Sheet1", 0, 0);
		String password = Excel.Testdata(Path, "Sheet1", 0, 1);
		
		loginPage = new Login(driver);
		loginPage.login(username, password);
		
		Thread.sleep(3000);
		
		String name = Excel.Testdata(Path, "Sheet3", 2, 7);
		String contact = Excel.Testdata(Path, "Sheet3", 2, 8);
		String avgBill = Excel.Testdata(Path, "Sheet3", 2, 9);
		String address = Excel.Testdata(Path, "Sheet3", 2, 10);
		String req = Excel.Testdata(Path, "Sheet3", 2, 11);
		String upl = Excel.Testdata(Path, "Sheet3", 2, 11);
		solPage = new Solar_CRM(driver);
		solPage.solar(name, contact, avgBill, address, req, upl);
		Thread.sleep(10000);
		
		
	}
}
