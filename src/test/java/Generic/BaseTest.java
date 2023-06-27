package Generic;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

//import Screenshots.ScreenShot;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;


public class BaseTest implements Auto_Constant
{

	  protected  static WebDriver driver;
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
	
		
		@AfterMethod(alwaysRun=true)
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException, InterruptedException 
		{
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]")) + ".png");
			//File file = new File(SFolderpath + testCaseName + ".png");
			FileUtils.copyFile(src, file);
			System.out.println("Screenshot taken at" +System.currentTimeMillis());
			//return SFolderpath + testCaseName + ".png";
			return SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]") +".png");
			
		}
		
//	
//	  @AfterMethod() public void tearDownMethod(ITestResult result) throws
//	  IOException, InterruptedException {
//	  
//	  if(result.getStatus()==ITestResult.FAILURE) 
//	  { 
//		  ScreenShot.screenshotFailure();
//	  }
//	  
//	  }
	 
		   
//	@AfterClass
//	public void closeBrowser() {
//		 driver.close();
//	}
    
}
