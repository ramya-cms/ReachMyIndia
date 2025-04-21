package POM_Classes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Generic.BaseTest;

public class DDE extends BaseTest{
	
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
	    
	    @FindBy(id="PartyPrimaryEmailId")
	    private WebElement email;
	    
	    @FindBy(xpath="//input[@id='PartyPrimaryMobileNo']")
	    private WebElement mob;
	    
	    @FindBy(xpath="//select[@id='OfficialValidDocType']")
	    private WebElement dtype;
	    
	    @FindBy(xpath="//input[@id='OfficialValidDocIDValue']")
	    private WebElement docid;
	    
	    @FindBy(id="PartyPermAddress")
	    private WebElement addr;
	    
	    @FindBy(id="PartyPermPincode")
	    private WebElement pin;
	    
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
	    
	    @FindBy(id="EntityName")
	    private WebElement ename;
	    
	    @FindBy(id="EntityEmailId")
	    private WebElement emaeil;
	    
	    @FindBy(id="EntityContactNo")
	    private WebElement emob;
	    
	    
	    @FindBy(id="EntityPANNumber")
	    private WebElement pan;
	    
	    
	    @FindBy(id="EntityLegalConstitution")
	    private WebElement legal;
	    
	    @FindBy(id="DateOfIncorporation")
	    private WebElement dc;
	    
	    
	    @FindBy(id="RegisteredOfficeAddress")
	    private WebElement raddr;
	    
	    @FindBy(id="RegisteredOfficeAddressPinCode")
	    private WebElement rpin;
	    
	    @FindBy(id="IsAddressSame")
	    private WebElement ofchkadr;
	    
	    @FindBy(id="CommunicationAddress")
	    private WebElement caddr;
	    
	    @FindBy(id="CommunicationAddressPinCode")
	    private WebElement cpin;
	    
	    @FindBy(id="chkTerms")
	    private WebElement chk;
	    
	    @FindBy(id="CKYC")
	    private WebElement ckyc;
	    
	    @FindBy(id="PartyCIN")
	    private WebElement cin;
	    
	    @FindBy(xpath="//button[@class='btn btn-danger']")
	    private WebElement close;
	    
	    @FindBy(xpath="//input[@id='Continue']")
	    private WebElement conti;
	   
	    
	    @FindBy(xpath="//input[@id='btnSubmit']")
	    private WebElement paynow;
	    
	    
	   	    public DDE(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        PageFactory.initElements(driver, this);
	        this.js = (JavascriptExecutor) driver;
	    }
	   	    
	   	    
	   	 public void details(String docn,String path, String stat, String name, String sename, String art, String cpric) throws IOException, InterruptedException 
	   	 {
	   	    scrollToElement(dde);
	   	    clickElement(dde, "Digital Agreement Signing");

	   	    clickElement(dde1, "Digital Agreement Signing");
	   	    wait.until(ExpectedConditions.visibilityOf(doc));

	   	    fillField(doc, docn, "Document Name");
	   	  
	   	    uploadFile(upload, path);

	   	    clickElement(stamp, "Estamp Selection");
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

	   	    
	   	 
	   	 public void signatory(String mobile,String dt,String doc,String db) throws InterruptedException
	   	 {
	   		 fillField(mob, mobile, "Mobile No");
	   		 
	   		 selectDropdownByText(dtype,dt);
	   		 
	   		 fillField(docid,doc,"Doc Id");
	   		 
	   		 fillField(dob,db,"Date of birth");
	   		 
	   		 scrollToElement(add);
	   		 
	   		 clickElement(add,"Add signatory");
	   		 Thread.sleep(2000);
	   		 
	   		 scrollToElement(cont1);
	   		 
	   		 clickElement(cont1, "Continue Button");
	   	     Thread.sleep(2000);
	   	 }
	   	    
	   	 
	   	 public void otherdetails() throws InterruptedException
	   	 {
	   		 scrollToElement(chk);
	   		 
	   		 clickElement(chk,"Check");
	   		 
	   		 clickElement(close,"Close");
	   		 
	   		 clickElement(conti,"Continu");
	   		 
	   		 Thread.sleep(3000);
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

	     private void clickElement(WebElement element, String elementName) {
	         try {
	             // Wait for the element to be clickable and click
	             wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	         } catch (ElementClickInterceptedException e) {
	             System.out.println(elementName + " is intercepted. Clicking via JavaScript.");
	             js.executeScript("arguments[0].click();", element);
	         } catch (TimeoutException e) {
	             throw new RuntimeException(elementName + " is not clickable within the timeout period.", e);
	         }
	     }
	     
	     	 private void uploadFile(WebElement fileInput, String path) throws IOException {
	         File file = new File(path);
	         Assert.assertTrue(file.exists(), "File does not exist: " + path);

	         fileInput.sendKeys(path);

	         String actualFilePath = fileInput.getAttribute("value");
	         Assert.assertNotNull(actualFilePath, "File upload failed");
	         Assert.assertTrue(actualFilePath.endsWith(file.getName()), "Uploaded file mismatch");
	     }
	    
	     	 
}
