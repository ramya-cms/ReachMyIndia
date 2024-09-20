package POM_Classes;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.BaseTest;

public class TVS_Service extends BaseTest
{

		WebDriver driver;
		WebDriverWait wait;
		
		@FindBy(xpath="//h4[normalize-space()='TVS Loan']")
		private WebElement tvs;
		
		@FindBy(xpath="//input[@id='FullName']")
		private WebElement name;
		
		@FindBy(xpath="//input[@id='Phone']")
		private WebElement phone;
		
		
		@FindBy(xpath="//input[@id='Mobile']")
		private WebElement mobile;
		
		
		@FindBy(xpath="//input[@id='DateOfBirth']")
		private WebElement dob;
		
		
		@FindBy(xpath="//input[@id='Street1']")
		private WebElement add1;
		
		@FindBy(id="State")
		private WebElement state;
		
		@FindBy(id="District")
		private WebElement district;
		
		@FindBy(id="Taluka")
		private WebElement taluk;
		
		
		@FindBy(id="PinCode")
		private WebElement pincode;
		
		@FindBy(id="DateTimeOfMeeting")
		private WebElement date;
		
		
		@FindBy(id="FollowUpDate")
		private WebElement fdate;
		
		@FindBy(id="Product")
		private WebElement pname;
		
		@FindBy(xpath="//select[@id='AvailableTrack']")
		private WebElement atrack;
		
		@FindBy(id="DocumentType")
		private WebElement doctype;
		
		@FindBy(xpath="//input[@id='DocId']")
		private WebElement chfile;
		
		
		@FindBy(xpath="//input[@id='Continue']")
		private WebElement cont;
		
		
		@FindBy(xpath="//a[normalize-space()='Sign out']")
		private WebElement logout;
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		
		
		public TVS_Service(WebDriver driver) 
		{
			this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
		
}
		
		
		public void details(String fname, String ph, String mob, String db,String add, String pincde,String dat,String fdat)

				throws InterruptedException, IOException {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0,850)", "");
		        
		     // Scroll the submit button into view
		        JavascriptExecutor js1 = (JavascriptExecutor) driver;
		        js1.executeScript("arguments[0].scrollIntoView(true);", tvs);
		        Thread.sleep(1000); // Just to ensure the scroll has completed

		        // Try clicking the submit button
		        try {
		            wait.until(ExpectedConditions.elementToBeClickable(tvs)).click();
		        } catch (ElementClickInterceptedException e) {
		            // Use JavaScript click as a fallback
		            js1.executeScript("arguments[0].click();", tvs);
		        }


		     wait.until(ExpectedConditions.visibilityOf(name)).sendKeys(fname);

		     wait.until(ExpectedConditions.visibilityOf(phone)).sendKeys(ph);
			
		     wait.until(ExpectedConditions.visibilityOf(mobile)).sendKeys(mob);
		     wait.until(ExpectedConditions.visibilityOf(dob)).sendKeys(db);	 
		     wait.until(ExpectedConditions.visibilityOf(add1)).sendKeys(add);
			
			
		
			Select sel = new Select(state);
			sel.selectByIndex(1);

			Thread.sleep(2000);

			Select sel1 = new Select(district);
			sel1.selectByVisibleText("HAPUR");

			Thread.sleep(2000);

			Select drop = new Select(taluk);
			drop.selectByVisibleText("DHAULANA");
			
			wait.until(ExpectedConditions.visibilityOf(pincode)).sendKeys(pincde);
		
			
			wait.until(ExpectedConditions.visibilityOf(date)).sendKeys(dat);	 
			wait.until(ExpectedConditions.visibilityOf(fdate)).sendKeys(fdat);	 
			
			Select sel2 = new Select(pname);
			sel2.selectByIndex(2);
			
			Thread.sleep(2000);
			Select sel3 = new Select(atrack);
			sel3.selectByIndex(1);
			Thread.sleep(2000);
			
			Select sel4 = new Select(doctype);
			sel4.selectByValue("mx_Loan_Statement_Copy");
			
			 // Scroll the submit button into view
	        JavascriptExecutor js2 = (JavascriptExecutor) driver;
	        js2.executeScript("arguments[0].scrollIntoView(true);", chfile);
	        Thread.sleep(1000); // Just to ensure the scroll has completed

			
			try {
	            wait.until(ExpectedConditions.visibilityOf(chfile)).sendKeys("D:\\Ramya\\Ramya Downloads\\190214.pdf");
	            
	        } catch (ElementClickInterceptedException e) {
	            // Use JavaScript click as a fallback
	            js2.executeScript("arguments[0].click();", chfile);
	        }

      
            Thread.sleep(2000);
            
            
            try {
	            wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
	        } catch (ElementClickInterceptedException e) {
	            // Use JavaScript click as a fallback
	            js1.executeScript("arguments[0].click();", cont);
	        }
         
			
            wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
			
}
		}
