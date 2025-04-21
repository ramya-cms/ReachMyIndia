package POM_Classes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Generic.Base_Page;

public class DDE_Entity extends Base_Page
{
	
		WebDriver driver;
		WebDriverWait wait;
		JavascriptExecutor js;
	
		@FindBy(xpath="//img[@src='images/digitalSig.webp']")
	    private WebElement dde ;
	    
	    
	    @FindBy(xpath ="//p[normalize-space()='Digital Agreement Signing']")
	    private WebElement dde1;
	    
	    @FindBy(xpath="//input[@id='DocumentDescription']")
	    private WebElement doc;
	    
	    @FindBy(xpath="//input[@id='DocId']")
	    private WebElement upload;
	    
	    @FindBy(xpath="//input[@id='EStampFlagYes']")
	    private WebElement stamp;
	    
	    @FindBy(xpath="//input[@id='Commercial']")
	    private WebElement entity;
	    
	    @FindBy(xpath="//select[@id='State']")
	    private WebElement state;
	    
	    @FindBy(name="FirstPartyName")
	    private WebElement fname;
	    
	    @FindBy(id="SecondPartyName")
	    private WebElement sname;
	    
	    @FindBy(xpath="//select[@id='ArticleCode']")
	    private WebElement article;
	    
	    @FindBy(xpath="//input[@id='ConsiderationPrice']")
	    private WebElement cprice;
	    
	    @FindBy(id="StampDutyAmount")
	    private WebElement stamount;
	    
	    @FindBy(id="DescriptionOfDocument")
	    private WebElement desc;
	    
	    @FindBy(id="StampDutyPaidBy")
	    private WebElement stpdby;
	    
	    @FindBy(xpath="//a[@class='btn btn-primary btnNext']")
	    private WebElement cont;
	    
	    @FindBy(id="PartyName")
	    private WebElement partyname;
	    
	  //  @FindBy(id="PartyPrimaryEmailId")
	  //  private WebElement email;
	    
	    @FindBy(xpath="//input[@id='PartyPrimaryMobileNo']")
	    private WebElement mob;
	    
	    @FindBy(xpath="//select[@id='OfficialValidDocType']")
	    private WebElement dtype;
	    
	    @FindBy(xpath="//input[@id='OfficialValidDocIDValue']")
	    private WebElement docid;
	    
	    @FindBy(id="PartyPermAddress")
	    private WebElement addr;
	    
	    @FindBy(id="PartyPermPincode")
	    private WebElement pinc;
	    
	    @FindBy(id="SignatoryDOB")
	    private WebElement dob;
	    
	    @FindBy(id="PartyLegalConstitution")
	    private WebElement legconst;
	    
	    @FindBy(id="PartyType")
	    private WebElement ptype;
	    
	    @FindBy(id="SignatoryGenderYes")
	    private WebElement male;
	    
	    @FindBy(id="SignatoryGenderNo")
	    private WebElement female;
	    
	    @FindBy(id="addParticipant")
	    private WebElement add;
	    
	    @FindBy(xpath="//a[@id='btnPartDtlsContinue']")
	    private WebElement cont1;
	    
	   
	
	@FindBy(xpath="//input[@id='EntityName']")
	private WebElement name;
	
	@FindBy(xpath="//input[@id='EntityEmailId']")
	private WebElement emailid;
	
	@FindBy(xpath="//input[@id='EntityContactNo']")
	private WebElement cnum;
	
	@FindBy(xpath="//input[@id='EntityPANNumber']")
	private WebElement epan;
	
	@FindBy(xpath="//select[@id='EntityLegalConstitution']")
	private WebElement legal;
	
	@FindBy(xpath="//input[@id='DateOfIncorporation']")
	private WebElement dincorp;
	
	@FindBy(xpath="//textarea[@id='RegisteredOfficeAddress']")
	private WebElement address;
	
	@FindBy(xpath="//input[@id='RegisteredOfficeAddressPinCode']")
	private WebElement pin;
	
	@FindBy(xpath="//input[@id='IsAddressSame']")
	private WebElement check;
	
	@FindBy(xpath="//textarea[@id='CommunicationAddress']")
	private WebElement caddr;
	
	@FindBy(xpath="//input[@id='CommunicationAddressPinCode']")
	private WebElement cpin;
	
	@FindBy(xpath="//input[@id='chkTerms']")
	private WebElement chk;
	
	@FindBy(xpath="//button[@class='btn btn-danger']")
	private WebElement close;
	
	@FindBy(xpath="//input[@id='Continue']")
	private WebElement pay;
	
	@FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement paynow;
	
	public DDE_Entity(WebDriver driver)
	{
		this.driver=driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor) driver;
	}
	
	
	
	
	 public void details(String docn,String path, String stat, String name, String sename, String art, String cpric) throws IOException, InterruptedException {
	   	    scrollToElement(dde);
	   	    clickElement(dde, "Digital Agreement Signing");

	   	    clickElement(dde1, "Digital Agreement Signing");
	   	    wait.until(ExpectedConditions.visibilityOf(doc));

	   	    fillField(doc, docn, "Document Name");
	   	  
	   	    uploadFile(upload, path);

	   	    clickElement(stamp, "Estamp Selection");
	   	    
	   	    clickElement(entity,"Entity");
	   	    selectDropdownByText(state, stat);

	   	    fillField(fname, name, "First Party Name");
	   	    fillField(sname, sename, "Second Party Name");

	   	    scrollToElement(article);
	   	    
	   	    clickElement(article,"Article Code");
	   	   
	   	    selectDropdownByText(article, art);

	   	    Thread.sleep(2000);
	   	    
	   	    fillField(cprice, cpric, "Consideration Price");
	   	    
	   	    Thread.sleep(5000);
	   	    
	   	    clickElement(stamount,"Stamp Duty Amount");
	   	    
	   	    clickElement(desc,"Description");
	   	    
	   	    Thread.sleep(2000);
	   	    
	   	    scrollToElement(cont);
	   	    
	   	    clickElement(cont, "Continue Button");
	   	    Thread.sleep(2000);
	   	}

	   	    
	   	 
	   	 public void signatory(String mobile,String dt,String doc,String db,String lg) throws InterruptedException
	   	 {
	   		 
	   		 
	   		 fillField(mob, mobile, "Mobile No");
	   		 
	   		 selectDropdownByText(dtype,dt);
	   		 
	   		 fillField(docid,doc,"Doc Id");
	   		 
	   		 fillField(dob,db,"Date of birth");
	   		 
	   		 selectDropdownByText(legconst,lg);
	   		 
	   		 scrollToElement(add);
	   		 
	   		 clickElement(add,"Add signatory");
	   		 Thread.sleep(2000);
	   		 
	   		 scrollToElement(cont1);
	   		 
	   		 clickElement(cont1, "Continue Button");
	   	     Thread.sleep(2000);
	   	 }
	   	    
	   	 
	
	   	public void payment()
	   	 {
	   		 scrollToElement(paynow);
	   		 
	   		 clickElement(paynow,"Pay Now");
	   	 }
	
	 private void fillField(WebElement element, String value, String fieldName) {
         Assert.assertTrue(element.isDisplayed(), fieldName + " field is not displayed");
         Assert.assertTrue(element.isEnabled(), fieldName + " field is not enabled");
         wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
     }

     private void selectDropdownByText(WebElement element, String text) {
         Select dropdown = new Select(element);
         dropdown.selectByVisibleText(text);
         Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), text,
                 "Dropdown selection is incorrect for " + element);
     }
     
  // Scroll and click with enhanced handling
     private void scrollToElement(WebElement element) {
         js.executeScript("arguments[0].scrollIntoView(true);", element);
     }

     
     	 private void uploadFile(WebElement fileInput, String path) throws IOException {
         File file = new File(path);
         Assert.assertTrue(file.exists(), "File does not exist: " + path);

         fileInput.sendKeys(path);

         String actualFilePath = fileInput.getAttribute("value");
         Assert.assertNotNull(actualFilePath, "File upload failed");
         Assert.assertTrue(actualFilePath.endsWith(file.getName()), "Uploaded file mismatch");
     }
     	 
     	private void clickElement(WebElement element, String elementName) {
     	    try {
     	        // Scroll to the element
     	        js.executeScript("arguments[0].scrollIntoView(true);", element);
     	        
     	        // Wait until clickable and click
     	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
     	    } catch (ElementClickInterceptedException e) {
     	        System.out.println(elementName + " is intercepted. Attempting JavaScript click.");
     	        js.executeScript("arguments[0].click();", element);
     	    } catch (TimeoutException e) {
     	        throw new RuntimeException(elementName + " is not clickable within the timeout period.", e);
     	    } catch (Exception e) {
     	        throw new RuntimeException("Unexpected error while clicking on " + elementName, e);
     	    }
     	}

    
     	public void ent_details(String nam, String emid, String cno, String pann, String legl, String incor,
     	                        String add, String pincode) throws InterruptedException {

     	    wait.until(ExpectedConditions.visibilityOf(name)).sendKeys(nam);
     	    
     	  //  wait.until(ExpectedConditions.elementToBeClickable(emailid)).click();
     	    
     
     	    wait.until(ExpectedConditions.visibilityOf(emailid)).sendKeys(emid);
     	    
     	    wait.until(ExpectedConditions.visibilityOf(cnum)).sendKeys(cno);
     	    
     	    wait.until(ExpectedConditions.visibilityOf(epan)).sendKeys(pann);
     	    
     	    wait.until(ExpectedConditions.visibilityOf(legal)).sendKeys(legl);
     	    
     	    wait.until(ExpectedConditions.visibilityOf(dincorp)).sendKeys(incor);
     	    
     	    wait.until(ExpectedConditions.visibilityOf(address)).sendKeys(add);
     	    
     	    wait.until(ExpectedConditions.visibilityOf(pin)).sendKeys(pincode);

     	    // Click the checkbox
     	    clickElement(check, "Is Address Same Checkbox");
     	    
     	    Thread.sleep(2000);

     	    // Click the terms checkbox
     	    clickElement(chk, "Terms Checkbox");
     	    
     	    clickElement(close, "Close");
     	   
     	    Thread.sleep(2000);
     	    // Click the Continue button
     	    clickElement(pay, "Continue Button");
     	}

		
}
