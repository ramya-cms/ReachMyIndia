package RMITestScript;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.MasterFranchisee;
import POM_Classes.MasterOP;

public class MasterOPScript extends BaseTest
{
	
	private MasterOP mop;
	
	private Login mflog;
	
	@Test(priority=2)
	public void reg() throws InterruptedException
	{
		driver = getDriver(); 
		String username = Excel.Testdata(Path, "Sheet1", 14, 1);
	    String password = Excel.Testdata(Path, "Sheet1", 14, 2);
	    String fdt = Excel.Testdata(Path, "Sheet3", 20, 1);
	    String srch = Excel.Testdata(Path, "Sheet3", 20, 2);
	    
        String com = Excel.Testdata(Path, "Sheet3", 20, 3);
        Thread.sleep(1000);
        
        mflog = new Login(driver);
        mflog.login(username, password);
        Thread.sleep(1000);
        
        mop = new MasterOP(driver);
        Thread.sleep(1000);
        mop.form(fdt,srch);
        Thread.sleep(1000);
        mop.appln(com);
        Thread.sleep(1000);
        mop.logout();
        Thread.sleep(1000);

        

	}       		

}
