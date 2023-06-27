package Generic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG implements Auto_Constant
{
	public static ExtentReports getReportObject()
	{
		//String path = System.getProperty("user.dir")+"//test-output//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(rpath);
		reporter.config().setReportName("Web automation results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ram");
		extent.createTest(rpath);
		return extent;
	}
}
