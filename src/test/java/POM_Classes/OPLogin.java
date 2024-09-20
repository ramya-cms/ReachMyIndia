package POM_Classes;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Generic.Base_Page;

public class OPLogin extends Base_Page {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath="//a[normalize-space()='Franchisee Verification/Inspection']")
    private WebElement verify;

    @FindBy(xpath="//input[@id='FromDate']")
    private WebElement date;

    @FindBy(xpath="//div[@class='datepicker-days']//th[@class='prev']")
    private WebElement left;

    @FindBy(xpath="//td[@class='day'][normalize-space()='1']")
    private WebElement dtsel;

    @FindBy(xpath="//select[@id='Status']")
    private WebElement status;

    @FindBy(xpath="//input[@id='btnShow']")
    private WebElement show;

    @FindBy(xpath="//div[@id='divResult']")
    private WebElement frame;

    @FindBy(xpath="//input[@type='search']")
    private WebElement search;

    @FindBy(xpath="//a[@id='viewAppl_1']")
    private WebElement sel;

    @FindBy(xpath="//textarea[@id='comment']")
    private WebElement comm;

    @FindBy(xpath="//select[@id='Action']")
    private WebElement action;

    @FindBy(xpath="//input[@id='btnSubmit']")
    private WebElement submit;

    //@FindBy(xpath="//input[@id='Back']")
    //private WebElement Backbtn;

    @FindBy(xpath="//*[@id=\"vdname\"]/section[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
    private WebElement logout;

    public OPLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void verification(String srch, String com) throws InterruptedException, IOException {
       
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        wait.until(ExpectedConditions.elementToBeClickable(verify)).click();
        wait.until(ExpectedConditions.elementToBeClickable(date)).click();
        wait.until(ExpectedConditions.elementToBeClickable(left)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dtsel)).click();

        Select stat = new Select(status);
        stat.selectByVisibleText("Under Scrutiny");

        wait.until(ExpectedConditions.elementToBeClickable(show)).click();
        wait.until(ExpectedConditions.visibilityOf(frame)).click();
        wait.until(ExpectedConditions.visibilityOf(search)).sendKeys(srch);
        wait.until(ExpectedConditions.elementToBeClickable(sel)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(comm)).sendKeys(com);
        Thread.sleep(2000);
        Select drop = new Select(action);
        drop.selectByVisibleText("Approved");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(submit)).click();
       // wait.until(ExpectedConditions.visibilityOf(Backbtn)).click();
       // wait.until(ExpectedConditions.visibilityOf(logout)).click();
    }
}
