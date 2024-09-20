package POM_Classes;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Generic.BaseTest;
import Generic.Email;
import Generic.ExtentReporterNG;

public class TestNGListeners extends BaseTest implements ITestListener
{	
	WebDriver driver;
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();

	 @Override
	    public void onTestStart(ITestResult result) {
	       test= extent.createTest(result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        test.log(Status.PASS, "Test Passed");
	    }
	    
	 
//	    @Override
//	    public void onTestFailure(ITestResult result) {
//	    	BaseTest baseTest = (BaseTest) result.getInstance();
//	      /*  try {
//				baseTest.getScreenshot(driver, result.getName());
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}*/
//	    	
//	    	test.fail(result.getThrowable());
//	    	try {
//				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
//						.get(result.getInstance());
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			
//			}
	    	

	    	String filePath=SFolderpath;
	    	/*try {
				filePath = getScreenshot(driver,result.getMethod().getMethodName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	    	//test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
       
	  //  }
	   
	    	@Override
	        public void onTestFailure(ITestResult result) {
	            Object testInstance = result.getInstance();

	            // Check if the test instance is of the type you expect
	            if (testInstance instanceof Email) {
	                Email emailTest = (Email) testInstance;
	                // Handle the failure for Email related tests
	                System.out.println("Email test failed.");
	                // Your logic here
	            } else if (testInstance instanceof BaseTest) {
	                BaseTest baseTest = (BaseTest) testInstance;
	                // Handle the failure for other tests
	                System.out.println("Base test failed.");
	                // Your logic here
	            } else {
	                System.out.println("Unhandled test instance type.");
	            }
	        }
	    @Override
			public void onTestSkipped(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	    
	    @Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	    @Override
		public void onStart(ITestContext context) {
			// TODO Auto-generated method stub
			
		}
	    
		@Override
		public void onFinish(ITestContext context) {
			// TODO Auto-generated method stub
			extent.flush();
			
		}
		
		
}