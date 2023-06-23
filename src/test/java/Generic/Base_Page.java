package Generic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class Base_Page {
 protected WebDriver driver;

	public void verifytitle(String Title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.titleContains(Title));
			Reporter.log("Title is matching", true);
		} catch (Exception e) {
			Reporter.log("Title not matching", true);
			Assert.fail();
		}
	}

	public void verifyElement(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOf(Element));
			Reporter.log("Element is present", true);
		} catch (Exception e) {
			Reporter.log("Element is not present", true);
			Assert.fail();

		}
	}

}
