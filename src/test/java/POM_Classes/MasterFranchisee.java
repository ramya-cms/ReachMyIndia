package POM_Classes;

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

import Generic.Base_Page;

public class MasterFranchisee extends Base_Page{
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	
	
	@FindBy(linkText="Activities")
	private WebElement act;
	
	@FindBy(xpath="//a[normalize-space()='Master Franchisee Registration']")
	private WebElement mfr;
	
	@FindBy(xpath="//select[@id='State']")
	private WebElement state;
	
	@FindBy(xpath="//select[@id='District']")
	private WebElement dist;
	

	@FindBy(xpath="//input[@id='Pincode']")
	private WebElement pin;
	

	@FindBy(xpath="//input[@id='Applicantname']")
	private WebElement apname;
	

	@FindBy(xpath="//select[@id='Gender']")
	private WebElement gen;
	

	@FindBy(xpath="//input[@id='Dateofbirth']")
	private WebElement dob;
	

	@FindBy(xpath="//input[@id='Mobilenumber']")
	private WebElement mno;
	

	@FindBy(xpath="//input[@id='Email']")
	private WebElement email;
	
	@FindBy(xpath="//select[@id='Plan']")
	private WebElement plan;
	
	
	@FindBy(xpath="//input[@id='Amount']")
	private WebElement amount;
	
	
	@FindBy(xpath="//textarea[@id='PresentAddress']")
	private WebElement paddr;
	
	@FindBy(xpath="//input[@id='IsAddressSame']")
	private WebElement sappr;
	
	@FindBy(xpath="//textarea[@id='PermenantAddress']")
	private WebElement praddr;
	
	@FindBy(xpath="//input[@id='Biodatadocfile']")
	private WebElement bio;
	
	@FindBy(xpath="//input[@id='Aadhardocfile']")
	private WebElement aad;
	
	@FindBy(xpath="//input[@id='Voteriddocfile']")
	private WebElement vot;
	
	
	@FindBy(xpath="//input[@id='PANdocfile']")
	private WebElement pan;
	
	
	@FindBy(xpath="//input[@id='btnRegistration']")
	private WebElement cont;
	
	@FindBy(xpath="//*[@id=\"vdname\"]/section[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
	private WebElement logout;

	
	 public MasterFranchisee(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        this.js = (JavascriptExecutor) driver;
	    }
	
	public void mform(String pincode,String name,String Dob,String Mno,String Email,String paddress) throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
        wait.until(ExpectedConditions.elementToBeClickable(act)).click();
       
        wait.until(ExpectedConditions.elementToBeClickable(mfr)).click();
       
		selectDropdownByVisibleText(state, "Karnataka");
		Thread.sleep(2000);
		
        selectDropdownByVisibleText(dist, "Bengaluru Urban");
        
        wait.until(ExpectedConditions.visibilityOf(pin)).sendKeys(pincode);
        wait.until(ExpectedConditions.visibilityOf(apname)).sendKeys(name);
        

        Select sel = new Select(gen);
        sel.selectByIndex(1); // Select gender
        
        wait.until(ExpectedConditions.visibilityOf(dob)).sendKeys(Dob);
        
        wait.until(ExpectedConditions.visibilityOf(mno)).sendKeys(Mno);
        
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(Email);
        

        Select sel1 = new Select(plan);
        sel1.selectByIndex(1); 
        
        wait.until(ExpectedConditions.visibilityOf(paddr)).sendKeys(paddress);

        clickWithFallback(sappr);
        js.executeScript("window.scrollBy(0,350)", "");
        
       // wait.until(ExpectedConditions.visibilityOf(praddr)).sendKeys(ofsddr);
        
        
	}
	
	 private void selectDropdownByVisibleText(WebElement element, String text) {
	        Select dropdown = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
	        dropdown.selectByVisibleText(text);
	    }
	
	
	 // Method to handle clicking with fallback for intercepted exceptions
	    private void clickWithFallback(WebElement element) {
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	        } catch (ElementClickInterceptedException e) {
	            js.executeScript("arguments[0].click();", element);
	        }
	    }
	
	
	  

	        // Method to upload multiple files
	        public void uploadMultipleFiles(String[] filePaths) throws InterruptedException {
	            WebElement[] fileInputs = { bio, aad, vot, pan }; // Add all file input elements

	            for (int i = 0; i < fileInputs.length; i++) {
	                try {
	                	  Thread.sleep(1000);
	                    wait.until(ExpectedConditions.visibilityOf(fileInputs[i])).sendKeys(filePaths[i]);
	                } catch (ElementClickInterceptedException e) {
	                    js.executeScript("arguments[0].click();", fileInputs[i]);  
	                    Thread.sleep(1000);
	                    wait.until(ExpectedConditions.visibilityOf(fileInputs[i])).sendKeys(filePaths[i]);
	                }
	            }
	        }
	        
	  
	        // Method to submit the form
	        public void submit() {
	            clickWithFallback(cont);
	        }
	        
	        public void logout()
	        {
	        	wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
	        }
	
	    }
	
