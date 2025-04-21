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

import Generic.BaseTest;

public class MasterOP extends BaseTest
{

	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	    
	@FindBy(xpath="//a[normalize-space()='Master Franchisee Registration Approval']")
	private WebElement verify;
	
	@FindBy(xpath="//input[@id='FromDate']")
	private WebElement fdate;
	
	@FindBy(xpath="//select[@id='Status']")
	private WebElement status;
	
	@FindBy(xpath="//input[@id='btnShow']")
	private WebElement show;
	
	@FindBy(xpath="//div[@id='divResult']")
    private WebElement frame;

    @FindBy(xpath="//input[@type='search']")
    private WebElement search;

    @FindBy(linkText="Process")
    private WebElement sel;
    
    @FindBy(xpath="//textarea[@id='comment']")
    private WebElement comm;

    @FindBy(xpath="//select[@id='Action']")
    private WebElement action;

    @FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement submit;

    @FindBy(xpath="//*[@id=\"vdname\"]/section[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
    private WebElement logout;

    public MasterOP(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
    }

    
    public void form(String fdat,String srch) throws InterruptedException
    
    {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(verify)).click();
        wait.until(ExpectedConditions.elementToBeClickable(fdate)).clear();
        wait.until(ExpectedConditions.visibilityOf(fdate)).sendKeys(fdat);
    

        Select stat = new Select(status);
        stat.selectByVisibleText("Pending with RMIOP");

        wait.until(ExpectedConditions.elementToBeClickable(show)).click();
        wait.until(ExpectedConditions.visibilityOf(frame)).click();
        wait.until(ExpectedConditions.visibilityOf(search)).sendKeys(srch);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(sel)).click();
    }
    
    public void appln(String com) throws InterruptedException
    {
    	 js.executeScript("window.scrollBy(0,350)", "");
    	 wait.until(ExpectedConditions.visibilityOf(comm)).sendKeys(com);
         Thread.sleep(2000);
         Select drop = new Select(action);
         drop.selectByVisibleText("Approve");
         Thread.sleep(2000);
         wait.until(ExpectedConditions.visibilityOf(submit)).click();
         Thread.sleep(2000);
         try {
             wait.until(ExpectedConditions.alertIsPresent());
             driver.switchTo().alert().accept();
             System.out.println("Alert accepted successfully.");
         } catch (org.openqa.selenium.NoAlertPresentException e) {
             System.out.println("No alert present after submission.");
         }
         Thread.sleep(2000);
         
    }
    
    public void logout()
    {
    	wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }
}
