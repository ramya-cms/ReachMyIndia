package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Generic.Base_Page;

public class Reports extends Base_Page
{
	
	 WebDriver driver;
	 WebDriverWait wait;
	 JavascriptExecutor js;
	
	@FindBy(xpath="//a[normalize-space()='Reports']")
	private WebElement rep;
	
	@FindBy(xpath="//a[normalize-space()='Transaction Report']")
	private WebElement tran;
	
	@FindBy(xpath="//input[@id='FromDate']")
	private WebElement frmdate;
	
	@FindBy(xpath="//td[normalize-space()='17']")
	private WebElement dat;
	
	@FindBy(xpath="//input[@id='ToDate']")
	private WebElement todate;
	
	@FindBy(xpath="//td[normalize-space()='17']")
	private WebElement tdat;
	
	@FindBy(xpath="//select[@id='Department']")
	private WebElement dept;
	
	@FindBy(xpath="//select[@id='ReportType']")
	private WebElement rtype;
	
	@FindBy(xpath="//input[@id='btnShow']")
	private WebElement show;
	
	
	public Reports(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
	}


	public void tranDetails(String Dep, String repType) throws InterruptedException
	{
		 wait.until(ExpectedConditions.elementToBeClickable(rep)).click();
		
		 wait.until(ExpectedConditions.elementToBeClickable(tran)).click();
		
		 Thread.sleep(3000);
		
		 wait.until(ExpectedConditions.elementToBeClickable(frmdate)).click();
		
		 wait.until(ExpectedConditions.elementToBeClickable(dat)).click();
		
		 wait.until(ExpectedConditions.elementToBeClickable(todate)).click();
		
		 wait.until(ExpectedConditions.elementToBeClickable(tdat)).click();
		 
		 selectDropdownByText(dept,Dep);
		 
		 selectDropdownByText(rtype,repType);
		
		 wait.until(ExpectedConditions.elementToBeClickable(show)).click();
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	     js.executeScript("window.scrollBy(0,580)", "");
		
		
	}
	
	private void selectDropdownByText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), text,
                "Dropdown selection is incorrect for " + element);
    }
	

}
