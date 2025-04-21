package RMITestScript;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.MasterFranchisee;


public class MasterFran_Script extends BaseTest {

	private MasterFranchisee mfr;
	WebElement bio,aad,vot,pan;
	
	private Login mflog;
	
	@Test(priority=1)
	public void reg() throws InterruptedException
	{
		driver = getDriver(); 
		String username = Excel.Testdata(Path, "Sheet3", 6, 3);
	    String password = Excel.Testdata(Path, "Sheet3", 6, 4);
	    
		
        String name = Excel.Testdata(Path, "Sheet3", 15, 2);
        String mob = Excel.Testdata(Path, "Sheet3", 15, 4);
        String email = Excel.Testdata(Path, "Sheet3", 15, 5);
        String dob = Excel.Testdata(Path, "Sheet3", 15, 3);
        String paddr = Excel.Testdata(Path, "Sheet3", 15, 6);
        String pcode = Excel.Testdata(Path, "Sheet3", 15, 1);
 
        
        Thread.sleep(1000);
        
        mflog = new Login(driver);
        mflog.login(username, password);
        Thread.sleep(1000);
        
        mfr = new MasterFranchisee(driver);
        mfr.mform(pcode,name,dob,mob,email,paddr);
        // Array of file paths to upload
        String[] filePaths = {
            "D:\\Ramya\\Ramya Downloads\\190214.pdf",
            "D:\\Ramya\\Ramya Downloads\\190214.pdf",
            "D:\\Ramya\\Ramya Downloads\\190214.pdf",
            "D:\\Ramya\\Ramya Downloads\\190214.pdf"
        };

        // Call the method to upload files
        mfr.uploadMultipleFiles(filePaths);
        

        mfr.submit();

	}       		
        		
}
