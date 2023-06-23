package POM_Classes;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Auto_Constant;
import Generic.Base_Page;

public class Reg_Form extends Base_Page implements Auto_Constant {
	static WebDriver driver;
	// WebDriverWait wait;

	@FindBy(id = "ApplicantName")
	private WebElement appName;

	@FindBy(id = "PanCardNumber")
	private WebElement Pan;

	@FindBy(xpath = "//input[@id='DateOfBirth']")
	private WebElement dob;

	@FindBy(xpath = "//td[@class='day weekend'][normalize-space()='2']")
	private WebElement date;

	@FindBy(xpath = "//select[@id='Gender']")
	private WebElement gen;

	@FindBy(xpath = "//select[@id='State']")
	private WebElement state;

	@FindBy(how = How.XPATH, using = "//select[@id='District']")
	private WebElement dist;

	@FindBy(xpath = "//input[@id='CommunicationAddress1']")
	private WebElement pradr;

	@FindBy(xpath = "//input[@id='CommunicationAddressPinCode']")
	private WebElement pincode;

	@FindBy(xpath = "//*[@id='IsAddressSame']")
	private WebElement spaddr;

	@FindBy(xpath = "//input[@id='IsAccept']")
	private WebElement cond;

	@FindBy(xpath = "//input[@id='btnRegistration']")
	private WebElement cont;

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public Reg_Form(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void details(String aname, String pan, String paddr, String pincde)
			throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(appName))
				.sendKeys(aname);

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(Pan)).sendKeys(pan);

		dob.click();
		date.click();

		Select sel = new Select(gen);
		sel.selectByIndex(1);

		Thread.sleep(2000);

		Select sel1 = new Select(state);
		sel1.selectByVisibleText("Karnataka");

		Thread.sleep(2000);

		Select drop = new Select(dist);
		drop.selectByVisibleText("Ballary");

		JavascriptExecutor js2 = (JavascriptExecutor) driver;
		js2.executeScript("window.scrollBy(0,350)", "");

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pradr)).sendKeys(paddr);

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(pincode))
				.sendKeys(pincde);

		js2.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(2000);

		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(spaddr))
				.click();
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(cond)).click();
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(cont)).click();

		// Navigate back two time
		Thread.sleep(6000);
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("window.history.go(-2)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	}

	// Scroll to Top
	public static void Screenshot() throws IOException, InterruptedException {
		JavascriptExecutor js4 = (JavascriptExecutor) driver;
		js4.executeScript("window.scrollTo(0, 0);");
		Long innerHeight = (Long) js4.executeScript("return window.innerHeight;");
		Long scroll = innerHeight;

		Long scrollHeight = (Long) js4.executeScript("return document.body.scrollHeight;");

		scrollHeight = scrollHeight + scroll;
		try {
			do {
				File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File destination = new File(SFolderpath + String.join("_", LocalDateTime.now().toString().split("[^A-Za-z0-9]")) + ".png");
				FileUtils.copyFile(screenshot, destination);
				js4.executeScript("window.scrollTo(0, " + innerHeight + ");");
				innerHeight = innerHeight + scroll;
			} while (scrollHeight >= innerHeight);
			System.out.println("ScreenShot Captured");
		} catch (IOException e) {
			System.out.println("Unble to capture ScreenShot" + e.getMessage());
		}
	}

}
