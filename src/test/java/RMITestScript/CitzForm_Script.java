package RMITestScript;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.CitzForm;
import POM_Classes.Login;


public class CitzForm_Script extends BaseTest
{
	
	private CitzForm ctfrm;
	private Login lgn;
	public static Logger log;
	WebDriverWait wait;
	
	
	@BeforeTest
	  public void setup() throws IOException {
        log = LogManager.getLogger(LoginScript.class.getName());
        driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

	
	@Test(priority=2)
	public void formdet() throws InterruptedException
	{
		log.debug("Testing valid scenario");
		Thread.sleep(3000);
		
		String nam = Excel.Testdata(Path, "Sheet4", 21, 3);
		String logn = Excel.Testdata(Path, "Sheet4", 21, 4);
		String pwrd = Excel.Testdata(Path, "Sheet4", 21, 5);
		String cpwrd = Excel.Testdata(Path, "Sheet4", 21, 6);
		
		ctfrm = new CitzForm(driver);
		ctfrm.form(nam, logn, pwrd, cpwrd);
		log.debug("Registration successful");
		
		
		  // Read test data from Excel
        String username = Excel.Testdata(Path, "Sheet4", 21, 4);
        String password = Excel.Testdata(Path, "Sheet4", 21, 5);

        // Initialize LoginTestCase and perform login
        lgn = new Login(driver);
        lgn.login(username, password);
        log.debug("Username and password entered");
        
	}

}
