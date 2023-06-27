package RMITestScript;

import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Registration;

//@Listeners(ScreenShot.TestNGListeners.class)
public class Registration_Test  extends BaseTest {
 
	
	private static Registration regis;

	@BeforeTest
	public  void setup() throws IOException, InterruptedException
	{	
		initialize();
		//TakeScreenShot ts=new TakeScreenShot(driver);
		//driver.close();
		
	}
	 @Test(priority=1)
     public static void RegPage() throws InterruptedException{
		    Thread.sleep(3000);
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		    String Mbnum = Excel.Testdata(Path, "Sheet1", 4, 0);
		    String EIDnum = Excel.Testdata(Path, "Sheet1", 4, 1);
		 
		     Thread.sleep(1000);
		     regis = new Registration(driver);
		     Thread.sleep(1000);
		     regis.Registration_page(Mbnum,EIDnum);
		    }
		

	}

 
