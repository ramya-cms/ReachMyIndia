package RMITestScript;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;

public class Test1 extends BaseTest {

	public static Logger log;
	
	@BeforeTest
	public void setup() throws IOException
	{	
		log=LogManager.getLogger(Test1.class.getName());
		//initialize();
		//log.debug("Browser got launch");
		
		//.close();
		
	}
	
	
	@Test(priority=1)
	public void loginPage() throws InterruptedException{
		//log=LogManager.getLogger(Test1.class);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		Thread.sleep(2000);
		String num1 = Excel.Testdata(Path, "Sheet1", 0, 0);
		String num2 = Excel.Testdata(Path, "Sheet1", 0, 1);

		Login l = new Login(driver);
		Thread.sleep(2000);
		log.debug("Navigated to application URL");
		l.login(num1, num2);
		log.debug("Username And Password got entered");
		log.debug("Login Successfully!");
		
		

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
