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
import Generic.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{	WebDriver driver;
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

	    @Override
	    public void onTestFailure(ITestResult result) {
	    	
	    	test.fail(result.getThrowable());
	    	try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
	    	
	    	String filePath=null;
			try {
				
					filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	       
	    }

	    @Override
			public void onTestSkipped(ITestResult result) {
	    	test.log(Status.SKIP, " Test skipped");
		}
	    
	    @Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			
		}
	    @Override
		public void onStart(ITestContext context) {
	    	String testName=context.getName();		
			test=extent.createTest("On Start Test Method" +testName);	
		}
	    
		@Override
		public void onFinish(ITestContext context) {
			String testName=context.getName();		
			test=extent.createTest("On Start Test Method" +testName);	
			extent.flush();
			//System.out.println(("Extent Reports Version 5  Test Suite is ending!"));
			
			
		}
		
		
}
