package Generic;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class ScreenShot extends BaseTest implements Auto_Constant {
	static WebDriver driver;
 
	public static void screenshotFailure() throws IOException, InterruptedException {
		Thread.sleep(5000);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(SFolderpath));
			Thread.sleep(3000);
			System.out.println("ScreenShot Captured");
		} catch (IOException e) {
			System.out.println("Unble to capture ScreenShot" + e.getMessage());
		}
	}
}

/* public static void ScreenshotScroll() throws IOException, InterruptedException {
JavascriptExecutor js4 = (JavascriptExecutor) driver;
js4.executeScript("window.scrollTo(0, 0);");
Long innerHeight = (Long) js4.executeScript("return window.innerHeight;");
Long scroll = innerHeight;

Long scrollHeight = (Long) js4.executeScript("return document.body.scrollHeight;");

scrollHeight = scrollHeight + scroll;
try {
	do {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File(
				SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]")) + ".png");
		FileUtils.copyFile(screenshot, destination);
		js4.executeScript("window.scrollTo(0, " + innerHeight + ");");
		innerHeight = innerHeight + scroll;
	} while (scrollHeight >= innerHeight);
	System.out.println("ScreenShot Captured");
} catch (IOException e) {
	System.out.println("Unble to capture ScreenShot" + e.getMessage());
}

}*/

