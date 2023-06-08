package Generic;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;

import java.util.Properties;


public class BaseTest implements Auto_Constant
{

	 protected WebDriver driver;
	 protected Properties prop;
		
		public BaseTest() {
			//this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
		@BeforeMethod(alwaysRun=true)
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
	
	
	
//	@AfterMethod
//	public void closeBrowser() {
//		 driver.close();
//	}
    
}
