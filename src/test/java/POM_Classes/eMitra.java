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
import org.testng.Assert;

import Generic.Base_Page;

public class eMitra extends Base_Page
{
	
	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	@FindBy(xpath="//a[normalize-space()='eMitra Registration']")
	private WebElement emitra;
	
	@FindBy(xpath="//select[@id='Applicationtype']")
	private WebElement atpye;
	
	@FindBy(xpath="//select[@id='Typeofcsclocation']")
	private WebElement loctype;
	
	@FindBy(xpath="//select[@id='State']")
	private WebElement state;
	
	@FindBy(xpath="//select[@id='District']")
	private WebElement dis;
	
	@FindBy(xpath="//select[@id='Municipality']")
	private WebElement munic;
	
	@FindBy(xpath="//select[@id='Taluk']")
	private WebElement taluk;
	
	@FindBy(xpath="//select[@id='Block']")
	private WebElement block;
	
	@FindBy(xpath="//select[@id='Grampanchayat']")
	private WebElement gpyt;
	
	@FindBy(xpath="//select[@id='Ward']")
	private WebElement ward;
	
	@FindBy(xpath="//select[@id='Village']")
	private WebElement village;
	
	@FindBy(xpath="//input[@id='Pincode']")
	private WebElement pincode;
	
	@FindBy(xpath="//input[@id='Applicantname']")
	private WebElement apname;
	
	@FindBy(xpath="//input[@id='Fathername']")
	private WebElement fname;
	
	@FindBy(xpath="//input[@id='Mothername']")
	private WebElement mname;
	
	@FindBy(xpath="//select[@id='Gender']")
	private WebElement gender;
	
	@FindBy(xpath="//select[@id='Maritalstatus']")
	private WebElement mstatus;
	
	@FindBy(xpath="//input[@id='Dateofbirth']")
	private WebElement dob;
	
	@FindBy(xpath="//input[@id='Mobilenumber']")
	private WebElement mnum;
	
	@FindBy(xpath="//input[@id='Alternatemobileno']")
	private WebElement altnum;
	
	@FindBy(xpath="//input[@id='Landline']")
	private WebElement land;
	
	@FindBy(xpath="//input[@id='Email']")
	private WebElement email;
	
	@FindBy(xpath="//textarea[@id='Address']")
	private WebElement addr;
	
	@FindBy(xpath="//input[@id='Pancardnumber']")
	private WebElement pan;
	
	@FindBy(xpath="//input[@id='JanAadharNo']")
	private WebElement jadar;
	
	@FindBy(xpath="//input[@id='Aadharcardno']")
	private WebElement aadhaar;
	
	@FindBy(xpath="//select[@id='BankDetailsProof']")
	private WebElement bnkproof;
	
	@FindBy(xpath="//select[@id='EducationQualification']")
	private WebElement edu;
	
	@FindBy(xpath="//input[@id='PoliceVerificationNo']")
	private WebElement polvef;
	
	@FindBy(xpath="//input[@id='KioskSsoId']")
	private WebElement kiosk;
	
	@FindBy(xpath="//select[@id='ComputerKnowledge']")
	private WebElement compkno;
	
	@FindBy(xpath="//select[@id='PresentBusiness']")
	private WebElement prbusiness;
	
	@FindBy(xpath="//input[@id='Aadhardocfile']")
	private WebElement uadhar;
	
	@FindBy(xpath="//input[@id='Voteriddocfile']")
	private WebElement ujan;
	
	@FindBy(xpath="//input[@id='PANdocfile']")
	private WebElement upan;
	
	@FindBy(xpath="//input[@id='ANYOTHERdocfile']")
	private WebElement ubnk;
	
	@FindBy(xpath="//input[@id='EDUCATIONdocfile']")
	private WebElement uedu;
	
	@FindBy(xpath="//input[@id='PROFFESSIONALdocfile']")
	private WebElement upol;
	
	@FindBy(xpath="//input[@id='COMPKNOWLEDGEdocfile']")
	private WebElement ucomp;
	
	@FindBy(xpath="//input[@id='IsAccept']")
	private WebElement chkbox;
	
	@FindBy(xpath="//input[@id='btnRegistration']")
	private WebElement submit;
	
	
	
	
	public eMitra(WebDriver driver)
	{
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
	}
	
	
	
	public void appDetails(String ltype,String district,String mun,String war,String pin) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(emitra)).click();		
		Thread.sleep(2000);
		selectDropdownByText(loctype,ltype);
		Thread.sleep(2000);
		selectDropdownByText(dis,district);
		Thread.sleep(2000);
		selectDropdownByText(munic,mun);
		Thread.sleep(2000);
		//selectDropdownByText(taluk,tal);
		
	//	selectDropdownByText(block,blk);
		
		//selectDropdownByText(gpyt,gpt);
		
		selectDropdownByText(ward,war);
		Thread.sleep(2000);
		//selectDropdownByText(village,vill);
		
		pincode.sendKeys(pin);
			
	}
	
	
	public void appInfo(String name,String gen,String msta,String db,String mob,String emil,String add,
			String paan,String adhr,String bnk,String edup,String poli,String kios,String prb)
	{
		scrollToElement(apname);
	    
		apname.sendKeys(name);
		
		selectDropdownByText(gender,gen);
		
		selectDropdownByText(mstatus,msta);
		
		dob.sendKeys(db);
		
		mnum.sendKeys(mob);
		
		email.sendKeys(emil);
		
		addr.sendKeys(add);
		
		pan.sendKeys(paan);
		
		aadhaar.sendKeys(adhr);
		
		selectDropdownByText(bnkproof,bnk);
		
		selectDropdownByText(edu,edup);
		
		polvef.sendKeys(poli);
		
		kiosk.sendKeys(kios);
		
		selectDropdownByText(prbusiness,prb);
		
		scrollToElement(uadhar);
	}
	
    // Method to upload multiple files
    public void uploadMultipleFiles(String[] filePaths) throws InterruptedException {
        WebElement[] fileInputs = { uadhar, ujan, upan, ubnk, uedu, upol, ucomp}; // Add all file input elements

        for (int i = 0; i < fileInputs.length; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(fileInputs[i])).sendKeys(filePaths[i]);
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", fileInputs[i]);
                wait.until(ExpectedConditions.visibilityOf(fileInputs[i])).sendKeys(filePaths[i]);
            }
        }
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
	
}
