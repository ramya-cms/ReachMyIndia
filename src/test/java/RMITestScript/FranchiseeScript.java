package RMITestScript;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.NewFranchiseeRegistration;

public class FranchiseeScript extends BaseTest
{
	private NewFranchiseeRegistration nfr;
	WebElement chfile,chifle1,chfile2,chfile3;
	
	@Test(priority=2)
	public void reg() throws InterruptedException
	{

        String name = Excel.Testdata(Path, "Sheet3", 9, 1);
        String pan = Excel.Testdata(Path, "Sheet3", 9, 2);
        String dob = Excel.Testdata(Path, "Sheet3", 9, 3);
        String paddr = Excel.Testdata(Path, "Sheet3", 9, 4);
        String pcode = Excel.Testdata(Path, "Sheet3", 9, 5);
        String saddr = Excel.Testdata(Path, "Sheet3", 9, 6);
        Thread.sleep(1000);
        
        
        nfr = new NewFranchiseeRegistration(driver);
        nfr.form(name, pan, dob, paddr, pcode, saddr);
        // Array of file paths to upload
        String[] filePaths = {
            "D:\\Ramya\\Ramya Downloads\\190214.pdf",
            "D:\\Ramya\\Ramya Downloads\\190214.pdf",
            "D:\\Ramya\\Ramya Downloads\\new.jpeg",
            "D:\\Ramya\\Ramya Downloads\\new.jpeg"
        };

        // Call the method to upload files
        nfr.uploadMultipleFiles(filePaths);
        
        nfr.opt();
        
        nfr.submit();

        		
        		
        		
        		
	}
}
