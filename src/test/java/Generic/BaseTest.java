package Generic;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class BaseTest implements Auto_Constant {

    public static WebDriver driver;
    public Properties prop;
    protected static Connection connection;
    
    public BaseTest() {
        
    }

    @BeforeSuite
    public void initialize() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/Config.properties";
        FileInputStream fin = new FileInputStream(path);
        prop = new Properties();
        prop.load(fin);
        fin.close();

        String strBrowser = prop.getProperty("browser");

        if (strBrowser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (strBrowser.equalsIgnoreCase("edge")) {
           
            EdgeOptions options = new EdgeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new EdgeDriver(options);
        }
       /* else
        {
        	driver=new FirefoxDriver();
        	
        }*/
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));
        
        PageFactory.initElements(driver, this);

        // Set up Database connection
        String dbUrl = "jdbc:sqlserver://10.9.246.163;databaseName=RMI";
        String username = "sa";
        String password = "$evKum#10361";
        
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getLoginUrl() {
        return prop.getProperty("loginUrl");
    }
    
    
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void getScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String screenshotName = "Failure_Screenshot_" + timestamp + ".png";
                String screenshotPath = SFolderpath + screenshotName;

                File destination = new File(screenshotPath);
                FileUtils.copyFile(source, destination);

                // Log the screenshot path for debugging
                System.out.println("Screenshot taken for failure: " + screenshotPath);

                // Adding screenshot path to ITestResult for use in listeners
                result.setAttribute("screenshotPath", screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void beforeMethod() {
        // Any setup that needs to run before each test method
    	driver = getDriver();
       // driver.get(getLoginUrl());
    }

  /* @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        // Clean up only cookies and session data if needed, do not quit the driver
        driver.manage().deleteAllCookies();
    }*/
    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }
    
	public void onTestFailure1(ITestResult result) {
		// TODO Auto-generated method stub
		
	}


	
}
