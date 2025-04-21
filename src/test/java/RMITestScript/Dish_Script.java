package RMITestScript;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.DDE_Entity;
import POM_Classes.Dish;
import POM_Classes.Login;

public class Dish_Script extends BaseTest
{
	
	private Login loginPage;
	private Dish dish;
	
	
	@Test(priority=1)
	public void dish_Details() throws InterruptedException
	{
		driver=getDriver();
		Thread.sleep(3000);
		
		String username = Excel.Testdata(Path, "Sheet1", 0, 0);
	    String password = Excel.Testdata(Path, "Sheet1", 0, 1);
	    
	    loginPage = new Login(driver);
        // Perform login
        loginPage.login(username, password);
        Thread.sleep(3000);
        
        String mob = Excel.Testdata(Path, "Sheet4", 17, 0);
        
        dish = new Dish(driver);
        dish.dist_tv(mob);
        
		
		
	}

}
