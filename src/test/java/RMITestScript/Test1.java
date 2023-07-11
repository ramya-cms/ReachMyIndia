package RMITestScript;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.Services;

public class Test1 extends BaseTest {

	public static Logger log;
	
	@BeforeTest
	public void setup() throws IOException
	{	

		log=LogManager.getLogger(Test1.class.getName());
		
	}
	
	
	@Test(priority=1,dataProvider="getLoginData")
	public void loginPage(String expectedResult) throws IOException, InterruptedException{
		
		Thread.sleep(2000);
		String num1 = Excel.Testdata(Path, "Sheet1", 0, 0);
		String num2 = Excel.Testdata(Path, "Sheet1", 0, 1);

		Login l = new Login(driver);
		Thread.sleep(2000);
		log.debug("Navigated to application URL");
		l.login(num1, num2);
		log.debug("Username And Password got entered");
		log.debug("Login Successfully!");
		
		
		Services serv = new Services(driver);
		String actualResult = null;
		
		try 
		{
			
		if(serv.Serv().isDisplayed())
		{
			 log.debug("User got logged in");
			   actualResult = "Success";
		}
		}catch(Exception e)
		{
			log.debug("User didn't log in");
			actualResult = "Failure";
		}
		
		
		if(actualResult.equals(expectedResult))
		{
			log.info("Login Test got passed");
			Assert.assertEquals(actualResult, expectedResult);
			
		}else {
			log.error("Login Test got failed");
		} 
		
			
	}
	
	
	@DataProvider
	public Object[][] getLoginData() {
		Object[][] data = {{"Success"},{"Failure"}};
		return data;
	}
	
	
//	@AfterTest
//	public void tearDown() {
//	 closeBrowser();
//  }
}
