package RMITestScript;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;

public class Test1 extends BaseTest {

	@BeforeTest
	public void setup() throws IOException
	{	
		initialize();
		driver.close();
		
	}
	
	
	//@Test(priority=1)
	public void loginPage() throws InterruptedException{

		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		Thread.sleep(2000);
		String num1 = Excel.Testdata(Path, "Sheet1", 0, 0);
		String num2 = Excel.Testdata(Path, "Sheet1", 0, 1);

		Login l = new Login(driver);
		Thread.sleep(2000);
		l.login(num1, num2);

	}
//	@Test(priority=2)
//	  public void screenshot1()
//	  {
//		  Assert.assertEquals(driver.getTitle(), "RMI");
//	  }
//	@AfterTest
//	public void tearDown() {
//	 closeBrowser();
//  }
}
