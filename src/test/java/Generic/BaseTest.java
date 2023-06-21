package Generic;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;

import java.util.Properties;


public class BaseTest implements Auto_Constant
{

	  protected static WebDriver driver;
	  protected Properties prop;
		
		public BaseTest() {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
		@BeforeSuite
		public void initialize() throws IOException  {
			
			String path=System.getProperty("user.dir")+"//src//test//resources//ConfigFile//Config.properties";
			
			FileInputStream fin = new FileInputStream(path);
			prop = new Properties();
			prop.load(fin);
			
		String strBrowser = prop.getProperty("browser");
			
		
		if(strBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (strBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	
		driver.get(prop.getProperty("url"));
	}
	
	
	
//	@AfterClass
//	public void closeBrowser() {
//		 driver.close();
//	}
    
}
