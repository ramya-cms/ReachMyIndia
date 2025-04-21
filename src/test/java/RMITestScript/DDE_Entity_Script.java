package RMITestScript;

import java.io.IOException;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;

import POM_Classes.DDE_Entity;
import POM_Classes.Login;

public class DDE_Entity_Script extends BaseTest
{

	
	private Login loginPage;
	//private static DDE dde;
	private DDE_Entity ddeEnt;
	
	
	@Test(priority=1)
	public void details() throws InterruptedException, IOException
	{
		
		driver = getDriver();
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
        
      
        String mob1 = Excel.Testdata(Path, "Sheet4", 8, 7);
        String dtype = Excel.Testdata(Path, "Sheet4", 8, 8);
        String docid = Excel.Testdata(Path, "Sheet4", 8, 9);
        String dob = Excel.Testdata(Path, "Sheet4", 8, 10);
        String leg = Excel.Testdata(Path, "Sheet4", 13, 4);
        
      
        
        String name = Excel.Testdata(Path, "Sheet4", 13, 0);
        String emailid =Excel.Testdata(Path, "Sheet4", 13, 1);
        String cont = Excel.Testdata(Path, "Sheet4", 13, 2);
        String pan = Excel.Testdata(Path, "Sheet4", 13, 3);
        String legal = Excel.Testdata(Path, "Sheet4", 13, 4);
        String dincorp = Excel.Testdata(Path, "Sheet4", 13, 5);
        String addr = Excel.Testdata(Path, "Sheet4", 13, 6);
        String pin = Excel.Testdata(Path, "Sheet4", 13, 7);
        
        ddeEnt = new DDE_Entity(driver);
        ddeEnt.details(docn, path, state, fname, sname, article, cprice);
        ddeEnt.signatory(mob1, dtype, docid, dob, leg);
        ddeEnt.ent_details(name, emailid, cont, pan, legal, dincorp, addr, pin);
        ddeEnt.payment();
        
        
        
	}
	
	
	
}
