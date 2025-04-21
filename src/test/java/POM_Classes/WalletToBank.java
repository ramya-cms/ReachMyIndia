package POM_Classes;

import java.sql.*;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic.Base_Page;

public class WalletToBank extends Base_Page {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Connection connection;

    @FindBy(xpath="//a[normalize-space()='Activities']")
    private WebElement act;

    @FindBy(xpath="//a[normalize-space()='Wallet To Bank Transfer Request']")
    private WebElement req;

    @FindBy(xpath="//input[@id='IsAccept']")
    private WebElement check;

    @FindBy(xpath="//input[@id='btnRegister']")
    private WebElement cont;

    @FindBy(xpath="//textarea[@id='Remarks']")
    private WebElement remarks;

    @FindBy(xpath="//input[@id='MobileNumber']")
    private WebElement Mnum;

    @FindBy(xpath="//input[@id='mobileotpsend']")
    private WebElement motp;

    @FindBy(xpath="//input[@id='MobileOTP']")
    private WebElement mobotp;

    @FindBy(xpath="//input[@id='EmailId']")
    private WebElement Emailid;

    @FindBy(xpath="//input[@id='emailotpsend']")
    private WebElement eotp;

    @FindBy(xpath="//input[@id='EmailOTP']")
    private WebElement emotp;

    @FindBy(xpath="//input[@id='TransferAmmount']")
    private WebElement totamt;

    @FindBy(xpath="//input[@id='Continue']")
    private WebElement con;
    
    @FindBy(xpath="//a[@role='button']")
    private WebElement log;
    
	@FindBy(xpath="//a[normalize-space()='Sign out']")
	private WebElement logout;

    public WalletToBank(WebDriver driver, Connection connection) {
        this.driver = driver;
        this.connection = connection; // Initialize database connection
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        this.js = (JavascriptExecutor) driver;
    }

    public void request(String rem) {
        wait.until(ExpectedConditions.elementToBeClickable(act)).click();
        wait.until(ExpectedConditions.elementToBeClickable(req)).click();
        wait.until(ExpectedConditions.elementToBeClickable(check)).click();
        wait.until(ExpectedConditions.elementToBeClickable(cont)).click();
        wait.until(ExpectedConditions.visibilityOf(remarks)).sendKeys(rem);

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,550)", "");
    }

    // Fetch OTP from the database
    private String getOtpFromDatabase(String tableName, String sendType, String sendTo) {
        String otp = null;
        String query = "SELECT TOP 1 OTP FROM " + tableName + " WHERE SENDTYPE='" + sendType + "' AND SENDTO='" + sendTo + "' ORDER BY CREATEDDATE DESC";

        System.out.println("Executing query: " + query);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                otp = resultSet.getString("OTP");
                System.out.println("Retrieved " + sendType + " OTP: " + otp);
            } else {
                System.out.println("No " + sendType + " OTP found in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otp;
    }

    public void enterMobileOtp() {
        try {
            if (Mnum.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(motp)).click();
                Thread.sleep(5000); // Wait for OTP generation

                String latestOtp = getOtpFromDatabase("CMN_LOG_OTP", "SMS", "9738526155");

                if (latestOtp != null) {
                    System.out.println("Entering Mobile OTP: " + latestOtp);
                    mobotp.clear(); // Clear old OTP before entering
                    mobotp.sendKeys(latestOtp);
                } else {
                    System.out.println("No Mobile OTP retrieved.");
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to enter mobile OTP: " + e.getMessage());
        }
    }

    public void enterEmailOtp() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(eotp)).click();
            Thread.sleep(5000); // Wait for OTP to generate

            String latestOtp = getOtpFromDatabase("CMN_LOG_OTP", "EMAIL", "testing678@yahoo.co.in");

            if (latestOtp != null) {
                System.out.println("Entering Email OTP: " + latestOtp);
                emotp.clear(); // Clear old OTP before entering
                emotp.sendKeys(latestOtp);
            } else {
                System.out.println("No Email OTP retrieved.");
            }
        } catch (Exception e) {
            System.out.println("Failed to enter email OTP: " + e.getMessage());
        }
    }

    public void clearOtpFields() {
        if (mobotp.isDisplayed() && mobotp.isEnabled()) {
            mobotp.clear();
        } else {
            System.out.println("Mobile OTP field is not interactable.");
        }

        if (emotp.isDisplayed() && emotp.isEnabled()) {
            emotp.clear();
        } else {
            System.out.println("Email OTP field is not interactable.");
        }
    }



    public void amount(String amt) {
    	wait.until(ExpectedConditions.visibilityOf(totamt)).clear();
        wait.until(ExpectedConditions.visibilityOf(totamt)).sendKeys(amt);
        wait.until(ExpectedConditions.elementToBeClickable(con)).click();
    }
    
    public void logout()
    {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
 	   wait.until(ExpectedConditions.elementToBeClickable(log)).click();
 	   wait.until(ExpectedConditions.elementToBeClickable(logout)).click();
    }
}

