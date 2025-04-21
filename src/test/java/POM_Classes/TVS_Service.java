package POM_Classes;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import Generic.BaseTest;

public class TVS_Service extends BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy(xpath = "//a[normalize-space()='Services']")
    private WebElement ser;

    @FindBy(xpath = "//h4[normalize-space()='TVS Loan']")
    private WebElement tvs;

    @FindBy(xpath = "//input[@id='FullName']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='Phone']")
    private WebElement phone;

    @FindBy(xpath = "//input[@id='Mobile']")
    private WebElement mobile;

    @FindBy(xpath = "//input[@id='DateOfBirth']")
    private WebElement dob;

    @FindBy(xpath = "//input[@id='Street1']")
    private WebElement add1;

    @FindBy(id = "State")
    private WebElement state;

    @FindBy(id = "District")
    private WebElement district;

    @FindBy(id = "Taluka")
    private WebElement taluk;

    @FindBy(id = "PinCode")
    private WebElement pincode;

    @FindBy(id = "DateTimeOfMeeting")
    private WebElement date;

    @FindBy(id = "FollowUpDate")
    private WebElement fdate;

    @FindBy(id = "Product")
    private WebElement pname;

    @FindBy(xpath = "//select[@id='AvailableTrack']")
    private WebElement atrack;

    @FindBy(id = "DocumentType")
    private WebElement doctype;

    @FindBy(xpath = "//input[@id='DocId']")
    private WebElement chfile;

    @FindBy(xpath = "//input[@id='Continue']")
    private WebElement cont;

    @FindBy(xpath = "//a[@role='button']")
    private WebElement log;

    @FindBy(xpath = "//a[normalize-space()='Sign out']")
    private WebElement logout;

    @FindBy(linkText = "Payment Acknowledgement")
    private WebElement receipt;

    @FindBy(xpath = "//div[@class='textleft padleft-1 fontBold']")
    private WebElement success;

    public TVS_Service(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
    }

    public void details(String fname, String ph, String mob, String db, String add, String stat,
            String dist, String tal, String pin, String prod, String attrack, String dtype, String path)
            throws IOException, InterruptedException {
        // Scroll to TVS Loan section
        scrollToElement(tvs);
        clickElement(tvs, "TVS Loan");

        // Fill in details with assertions
        fillField(name, fname, "Full Name");
        fillField(phone, ph, "Phone");
        fillField(mobile, mob, "Mobile");
        fillField(dob, db, "Date of Birth");
        fillField(add1, add, "Address");

        // Dropdown selections with assertions
        selectDropdownByText(state, stat);
        selectDropdownByText(district, dist);
        selectDropdownByText(taluk, tal);

        fillField(pincode, pin, "Pin Code");
        Thread.sleep(1000);

        // Select dates
        clickElement(date, "Meeting Date");
        Thread.sleep(1000);
        clickElement(fdate, "Follow-Up Date");
        Thread.sleep(1000);

        selectDropdownByText(pname, prod);
        selectDropdownByText(atrack, attrack);
        selectDropdownByText(doctype, dtype);

        // File upload
        uploadFile(chfile, path);

        // Continue and validate receipt
        clickElement(cont, "Continue");
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

    private void uploadFile(WebElement fileInput, String path) throws IOException {
        File file = new File(path);
        Assert.assertTrue(file.exists(), "File does not exist: " + path);

        fileInput.sendKeys(path);

        String actualFilePath = fileInput.getAttribute("value");
        Assert.assertNotNull(actualFilePath, "File upload failed");
        Assert.assertTrue(actualFilePath.endsWith(file.getName()), "Uploaded file mismatch");
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

    public void services() {
        clickElement(ser, "Services");
    }

    public void scroll() {
        js.executeScript("window.scrollTo(0, -550)");
        clickElement(ser, "Services after scrolling");
    }

    public void logout() {
        clickElement(log, "Logout");
        clickElement(logout, "Sign Out");
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout failed");
    }
}
