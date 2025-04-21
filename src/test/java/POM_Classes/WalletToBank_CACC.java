package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class WalletToBank_CACC  extends Base_Page
{
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath="//a[normalize-space()='Wallet To Bank Transfer Settlement']")
	private WebElement wtb_settlement;
	
	@FindBy(id="FromDate")
	private WebElement date;
	
	@FindBy(id="Status")
	private WebElement status;
	
	@FindBy(xpath="//input[@id='btnShow']")
	private WebElement show;
	
	@FindBy(xpath="//div[@id='divResult']")
    private WebElement frame;
	
	
	@FindBy(xpath="//input[@type='search']")
	private WebElement search;
	
	
	@FindBy(xpath="//tr[@class='odd']//a[@id='viewAppl_m.EncryptedId']")
	private WebElement sele;
	
	
	@FindBy(xpath="//select[@id='SettlementStatus']")
	private WebElement st_status;
	
	@FindBy(xpath="//input[@id='FromDate']")
	private WebElement set_bnk_refdate;
	
	@FindBy(xpath="//input[@id='ReferenceNo']")
	private WebElement bnk_ref_no;
	
	@FindBy(xpath="//div/textarea[@id='SettlementRemark']")
	private WebElement rem;
	
	@FindBy(xpath="//input[@id='btnSubmit']")
	private WebElement submit;
	
	  
    @FindBy(xpath="//*[@id=\"vdname\"]/section[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
    private WebElement logout;
    
	
	public WalletToBank_CACC(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
	}


	public void settlement(String dat,String stas,String srch,String stas1,String refdate, String bnk_ref, String remarks)
	{	
		wait.until(ExpectedConditions.elementToBeClickable(wtb_settlement)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(date)).clear();
		
		wait.until(ExpectedConditions.visibilityOf(date)).sendKeys(dat);
		
		Select sts = new Select(status);
		sts.selectByVisibleText(stas);
		
		wait.until(ExpectedConditions.elementToBeClickable(show)).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(frame)).click();
		
		wait.until(ExpectedConditions.visibilityOf(search)).sendKeys(srch);
		
		wait.until(ExpectedConditions.elementToBeClickable(sele)).click();
		
		Select sts1 = new Select(st_status);
		sts1.selectByVisibleText(stas1);
		
		wait.until(ExpectedConditions.elementToBeClickable(set_bnk_refdate)).clear();
		
		wait.until(ExpectedConditions.visibilityOf(set_bnk_refdate)).sendKeys(refdate);
		
		wait.until(ExpectedConditions.visibilityOf(bnk_ref_no)).sendKeys(bnk_ref);
		
		wait.until(ExpectedConditions.visibilityOf(rem)).sendKeys(remarks);
		
		wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
		
	    wait.until(ExpectedConditions.visibilityOf(logout)).click();
		
	}

}
