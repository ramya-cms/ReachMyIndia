package Generic;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

public class BaseTest implements Auto_Constant {

	public static WebDriver driver;
	public Properties prop;


	public BaseTest() {
		// this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@BeforeSuite
	public void initialize() throws IOException {

		String path = System.getProperty("user.dir") + "//src//test//resources//ConfigFile//Config.properties";

		FileInputStream fin = new FileInputStream(path);
		prop = new Properties();
		prop.load(fin);

		String strBrowser = prop.getProperty("browser");

		if (strBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (strBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(25));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get(prop.getProperty("url"));
	}



    @AfterMethod(alwaysRun = true)
   	public String getScreenshot(WebDriver driver, String testCaseName) throws IOException 
    {
       TakesScreenshot ts = (TakesScreenshot)driver;
       File source = ts.getScreenshotAs(OutputType.FILE);
       File file = new File(SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]")) + ".png");
      // File file=new File(SFolderpath+testCaseName+".png");
       FileUtils.copyFile(source, file);
       System.out.println("Screenshot Taken at "+System.currentTimeMillis());
       return SFolderpath + ".png";
     }


    @AfterSuite
	public void tearDown() {
		driver.close();
		if (driver != null) {
			driver.quit();
			
		}
    }
}



