package POM_Classes;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class Wallet_Recharge extends Base_Page
{
	
	WebDriver driver;
    WebDriverWait wait;
    private static final Logger log1 = LogManager.getLogger(Wallet_Recharge.class);
    
    @FindBy(xpath="//a[normalize-space()='Activities']")
    private WebElement activity;
    
    @FindBy(xpath="//a[normalize-space()='Wallet Recharge']")
    private WebElement wal_rech;
    
    @FindBy(xpath="//input[@id='RechargeAmount']")
    private WebElement rech_txt;
    
    @FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement submit;
    
    @FindBy(xpath="//a[@id='collapse_UNB']")
    private WebElement ibn;
    
    @FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement pay;
    
    @FindBy(xpath="//input[@id='btnCancel']")
    private WebElement cancel;
    
    @FindBy(xpath="//li[@id='net-banking-list-AXIB-pop']//img[@class='accrdn-arrw']")
    private WebElement axis;
    
    @FindBy(xpath="//li[@id='net-banking-list-AXIB-pop']//button[@type='button'][normalize-space()='PROCEED']")
    private WebElement proceed;
    
    @FindBy(xpath="//input[@id='username']")
    private WebElement user;
    
    @FindBy(xpath="//input[@id='password']")
    private WebElement pswd;
    
    @FindBy(xpath="//input[@type='submit']")
    private WebElement sub;
    
    @FindBy(xpath="//input[@name='authenticate']")
    private WebElement success;
    
    @FindBy(xpath="//input[@name='failure1']")
    private WebElement failure;
    
    @FindBy(xpath="//a[@role='button']")
    private WebElement log;
    
	@FindBy(xpath="//a[normalize-space()='Sign out']")
	private WebElement logout;
    
    public Wallet_Recharge(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    
    public void recharge(String amount) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(activity)).click();
        wait.until(ExpectedConditions.elementToBeClickable(wal_rech)).click();
        wait.until(ExpectedConditions.visibilityOf(rech_txt)).sendKeys(amount);
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        
    }
    
    public void bank()
    {
        wait.until(ExpectedConditions.elementToBeClickable(ibn)).click();

        // Scroll and attempt to click
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", pay);
        wait.until(ExpectedConditions.elementToBeClickable(pay));
        try {
            pay.click();
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", pay);
        }
    }

        
        
    
  public void axis(String username,String password) throws InterruptedException {
    	
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	wait.until(ExpectedConditions.elementToBeClickable(axis)).click();
        wait.until(ExpectedConditions.elementToBeClickable(proceed)).click();
        wait.until(ExpectedConditions.visibilityOf(user)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(pswd)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(sub)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(success)).click();
        Thread.sleep(3000);
  }
   public void logout()     
   {
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	   wait.until(ExpectedConditions.elementToBeClickable(log)).click();
	   wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }
    
   public List<WebElement> getErrorMessages() {
       log1.debug("Checking for error message elements");
       List<WebElement> errorElements = new ArrayList<>();
       
       // List of possible error message locators
       List<By> errorLocators = List.of(
           By.xpath("//div[contains(text(),'Please Enter Recharge Amount')]"),
           By.xpath("//div[contains(text(),'Minimum Recharge Amount is 100, Please enter Valid Recharge Amount')]")
       );
       
       for (By locator : errorLocators) {
           try {
               List<WebElement> elements = driver.findElements(locator);
               if (!elements.isEmpty()) {
                   for (WebElement element : elements) {
                       if (element.isDisplayed()) {
                           log1.debug("Error message found: " + element.getText().trim());
                           errorElements.add(element);
                       } else {
                           log1.debug("Found element but it is not displayed: " + locator);
                       }
                   }
               } else {
                   log1.debug("No elements found for locator: " + locator);
               }
           } catch (Exception e) {
               log1.debug("Exception while locating elements with locator: " + locator, e);
           }
       }
       
       if (errorElements.isEmpty()) {
           log1.debug("No error message elements found or not displayed");
       }
       
       return errorElements;
   }
    
}
