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

import Generic.Base_Page;

public class WalletToBank_SSM extends Base_Page
{
	   WebDriver driver;
	   WebDriverWait wait;
	    
	   
	   @FindBy(xpath="//a[normalize-space()='Activities']")
	   private WebElement activities;
	   
	   @FindBy(xpath="//a[normalize-space()='Wallet To Bank Transfer Approval']")
	   private WebElement approval;
	    
	   @FindBy(id="Status")
	   private WebElement status;
	   
	   @FindBy(xpath="//div[@id='divResult']")
	    private WebElement frame;

	    @FindBy(xpath="//input[@type='search']")
	    private WebElement search;
	    
	    @FindBy(xpath="//tbody/tr[1]/td[13]/a[1]")
	    private WebElement sel1;

	  
	    @FindBy(xpath="//select[@id='Action']")
	    private WebElement action;
	    
	    @FindBy(xpath="//textarea[@id='comment']")
	    private WebElement comm;


	    @FindBy(xpath="//input[@id='btnSubmit']")
	    private WebElement submit;
	    
	    @FindBy(xpath="//*[@id=\"vdname\"]/section[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
	    private WebElement logout;
	    
	    public WalletToBank_SSM(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	    }

	    public void activity()
	    {
	    	activities.click();
	    }
	    
	    public void approval(String srch,String com) throws InterruptedException
	    {
	    	approval.click();
	    	
	    	Select sel = new Select(status);
	    	sel.selectByVisibleText("Pending");
	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(frame)).click();
	        wait.until(ExpectedConditions.visibilityOf(search)).sendKeys(srch);
	        
	        wait.until(ExpectedConditions.elementToBeClickable(sel1)).click();
	        
	        Thread.sleep(2000);
	        
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,550)", "");
	        Thread.sleep(2000);
	        
	        Select drop = new Select(action);
	        drop.selectByVisibleText("Approve");
	        Thread.sleep(2000);
	        
	        
	        wait.until(ExpectedConditions.visibilityOf(comm)).sendKeys(com);
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
	        wait.until(ExpectedConditions.visibilityOf(logout)).click();
	    }
	
}
