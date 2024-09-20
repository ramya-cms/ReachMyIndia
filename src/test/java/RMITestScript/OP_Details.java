package RMITestScript;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.OPLogin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class OP_Details extends BaseTest {
    //private WebDriver driver;
	Login loginPage;
    private OPLogin opverify;
    WebDriverWait wait;

	@Test(priority=3)
    public void RegPage() throws InterruptedException, IOException
    {
       driver = getDriver(); // Get the initialized WebDriver instance
       wait = new WebDriverWait(driver, Duration.ofSeconds(20));
      
        // Fetching data from Excel
        String username = Excel.Testdata(Path, "Sheet1", 14, 1);
        String password = Excel.Testdata(Path, "Sheet1", 14, 2);
        String srch = Excel.Testdata(Path, "Sheet1", 15, 2);
        String com = Excel.Testdata(Path, "Sheet1", 15, 1);
        
        
        // Initialize the Login page with the driver
        loginPage = new Login(driver);
        Thread.sleep(2000);
        
        // Perform login
        loginPage.login(username, password);
        Thread.sleep(2000);
        
 
        opverify = new OPLogin(driver);
        Thread.sleep(2000);
        opverify.verification(srch,com);
        Thread.sleep(2000);
        
       
  }
}

