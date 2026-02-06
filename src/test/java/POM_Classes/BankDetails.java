package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import Generic.Base_Page;

public class BankDetails extends Base_Page {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@role='button']")
    private WebElement hover;

    @FindBy(xpath = "//a[normalize-space()='Profile']")
    private WebElement profile;

    @FindBy(xpath = "//a[@id='tabBankDetails']")
    private WebElement bank;

    @FindBy(id = "BankAccountHolderName")
    private WebElement name;

    @FindBy(id = "AccountNumber")
    private WebElement accno;

    @FindBy(id = "ReenterAccountNumber")
    private WebElement reaccno;

    @FindBy(id = "IFSCCode")
    private WebElement ifsc;

    @FindBy(id = "ReenterIFSCCode")
    private WebElement reifsc;

    @FindBy(id = "PanCard")
    private WebElement pan;

    @FindBy(id = "ReenterPanCard")
    private WebElement repan;

    @FindBy(id = "Bankemailotpsend")
    private WebElement otpbn;

    @FindBy(id = "BankEmailOTP")
    private WebElement otp;

    @FindBy(id = "BankCheque")
    private WebElement upload;

    @FindBy(id = "btnBankDetails")
    private WebElement submit;

    public BankDetails(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public void bnkDtls(String fname, String acn, String reacn, String ifsco, String reifsco, String pann, String repann) 
    		throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(hover)).click();
        wait.until(ExpectedConditions.elementToBeClickable(profile)).click();
        wait.until(ExpectedConditions.elementToBeClickable(bank)).click();

        name.sendKeys(fname);
        accno.sendKeys(acn);
        reaccno.sendKeys(reacn);
        ifsc.sendKeys(ifsco);
        reifsc.sendKeys(reifsco);
        pan.sendKeys(pann);
        repan.sendKeys(repann);

        wait.until(ExpectedConditions.elementToBeClickable(otpbn)).click();
        Thread.sleep(3000);
    }

    public void upload(String upldPath) {
        wait.until(ExpectedConditions.visibilityOf(upload)).sendKeys(upldPath);
    }

    public void cont() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submit));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
            Thread.sleep(500); // Allow any animation to complete
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
        } catch (Exception e) {
            System.out.println("Failed to click submit: " + e.getMessage());
        }
    }

}
