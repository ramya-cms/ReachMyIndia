package POM_Classes;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class NewFranchiseeRegistration extends Base_Page {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy(xpath = "//input[@id='ApplicantName']")
    private WebElement fname;

    @FindBy(xpath = "//input[@id='PanCardNumber']")
    private WebElement pan;

    @FindBy(id = "DateOfBirth")
    private WebElement Dob;

    @FindBy(xpath = "//select[@id='Gender']")
    private WebElement gen;

    @FindBy(xpath = "//select[@id='State']")
    private WebElement state;

    @FindBy(how = How.XPATH, using = "//select[@id='District']")
    private WebElement dist;

    @FindBy(xpath = "//select[@id='Taluk']")
    private WebElement taluk;

    @FindBy(xpath = "//select[@id='Mandal']")
    private WebElement hobli;

    @FindBy(xpath = "//select[@id='Village']")
    private WebElement village;

    @FindBy(xpath = "//input[@id='CommunicationAddress1']")
    private WebElement prsaddress;

    @FindBy(xpath = "//input[@id='CommunicationAddressPinCode']")
    private WebElement prepincode;

    @FindBy(xpath = "//*[@id='IsAddressSame']")
    private WebElement spaddr;

    @FindBy(xpath = "//textarea[@id='OfficeAddress']")
    private WebElement ofcaddr;

    @FindBy(xpath = "//input[@id='blankAadhaarProofFile']")
    private WebElement chfile;

    @FindBy(xpath = "//input[@id='blankAddressProofFile']")
    private WebElement chfile1;

    @FindBy(xpath = "//input[@id='blankPhotoFranchisee']")
    private WebElement chfile2;

    @FindBy(xpath = "//input[@id='blankPhotoShop']")
    private WebElement chfile3;

    @FindBy(xpath = "//input[@id='Franchisee']")
    private WebElement opt;

    @FindBy(xpath = "//input[@id='IsReferralCode']")
    private WebElement drefcode;

    @FindBy(xpath = "//input[@id='btnRegistration']")
    private WebElement cont;

    public NewFranchiseeRegistration(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    // Method to fill the form
    public void form(String f_name, String Pan, String dob, String paddress, String pincode, String ofsddr) {
        wait.until(ExpectedConditions.visibilityOf(fname)).sendKeys(f_name);
        wait.until(ExpectedConditions.visibilityOf(pan)).sendKeys(Pan);
        wait.until(ExpectedConditions.elementToBeClickable(Dob)).clear();
        wait.until(ExpectedConditions.visibilityOf(Dob)).sendKeys(dob);

        Select sel = new Select(gen);
        sel.selectByIndex(1); // Select gender

        selectDropdownByVisibleText(state, "Karnataka");
        selectDropdownByVisibleText(dist, "Bengaluru Urban");

        scrollAndSelect(taluk, "Anekal");
        scrollAndSelect(village, "Harohalli");

        wait.until(ExpectedConditions.visibilityOf(prsaddress)).sendKeys(paddress);
        wait.until(ExpectedConditions.visibilityOf(prepincode)).sendKeys(pincode);

        clickWithFallback(spaddr);
        wait.until(ExpectedConditions.visibilityOf(ofcaddr)).sendKeys(ofsddr);
        
       
    }

    // Method to select dropdown value
    private void selectDropdownByVisibleText(WebElement element, String text) {
        Select dropdown = new Select(wait.until(ExpectedConditions.visibilityOf(element)));
        dropdown.selectByVisibleText(text);
    }

    // Method to scroll and select a dropdown value
    private void scrollAndSelect(WebElement element, String text) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        selectDropdownByVisibleText(element, text);
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
        WebElement[] fileInputs = { chfile, chfile1, chfile2, chfile3 }; // Add all file input elements

        for (int i = 0; i < fileInputs.length; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(fileInputs[i])).sendKeys(filePaths[i]);
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", fileInputs[i]);
                wait.until(ExpectedConditions.visibilityOf(fileInputs[i])).sendKeys(filePaths[i]);
            }
        }
    }

    // Method to handle clicking on options
    public void opt() {
        clickWithFallback(opt);
        clickWithFallback(drefcode);
    }

    // Method to submit the form
    public void submit() throws InterruptedException {
        clickWithFallback(cont);
        // Wait for the masked screen to appear
     	Thread.sleep(30000);
    }
}
