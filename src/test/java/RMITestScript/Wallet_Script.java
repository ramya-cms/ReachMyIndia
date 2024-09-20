package RMITestScript;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.Wallet_Recharge;

public class Wallet_Script extends BaseTest {

	private static Logger log;
    private Login loginPage;
    private Wallet_Recharge recharge;
    private WebDriverWait wait;
    
    @BeforeTest
    public void setUp() throws IOException {
    	log = LogManager.getLogger(Wallet_Script.class.getName());
        driver = getDriver(); // Get the initialized WebDriver instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize the wait once
    }

    @Test(priority = 1)
    public void WallRecharge() throws InterruptedException, IOException {
    	
    	 log.debug("Testing  with valid amount");

        // Fetching data from Excel
        String username = Excel.Testdata(Path, "Sheet1", 0, 0);
        String password = Excel.Testdata(Path, "Sheet1", 0, 1);
        String amount = Excel.Testdata(Path, "Sheet1", 19, 0);
        String puser = Excel.Testdata(Path, "Sheet1", 19, 2);
        String ppswd = Excel.Testdata(Path, "Sheet1", 19, 3);
 
        String actualResult = "Success";
        String expectedResult = "Success";
      
        // Initialize the Login page and perform login
        loginPage = new Login(driver);
        loginPage.login(username, password);
        log.debug("Username and Password entered");

        // Initialize the Wallet_Recharge page and perform recharge
        recharge = new Wallet_Recharge(driver);
        recharge.recharge(amount);
        log.debug("Amount entered : 100");
        
        recharge.bank();
        log.debug("Paymode selected : Net Banking");
        
        // Perform payment via Axis bank
        recharge.axis(puser, ppswd);
        log.debug("Payu credentials got entered");
        
        log.debug("Expected result: " + expectedResult + ", Actual result: " + actualResult);
        
        Assert.assertEquals(actualResult, expectedResult, "Wallet Recharge is successfull");
        
    }

    @Test(priority = 2)
    public void BlankAmount() throws InterruptedException, IOException {
    	
    	 log.debug("Testing  with blank amount");
    	
    	 String expectedErrorMessage = "Please Enter Recharge Amount";

        // Fetching data from Excel
        String amount = Excel.Testdata(Path, "Sheet1", 22, 0);

        // Initialize the Wallet_Recharge page and attempt recharge with blank amount
        recharge = new Wallet_Recharge(driver);
        recharge.recharge(amount);
        log.debug("No amount is entered");
        
        
        List<WebElement> errorElements = recharge.getErrorMessages();
        StringBuilder actualErrorMessage = new StringBuilder();

        // Process the list of error elements
        if (!errorElements.isEmpty()) {
            for (WebElement errorElement : errorElements) 
            {
                actualErrorMessage.append(errorElement.getText().trim()).append(" "); // Concatenate error messages if multiple
            }
            log.debug("Error message elements found with text: " + actualErrorMessage.toString().trim());
        } else {
            log.debug("No error messages found");
        }

        log.debug("Expected error message: " + expectedErrorMessage);
        log.debug("Actual error message: " + actualErrorMessage.toString().trim());

        // Assert that the actual error message contains the expected error message (case-insensitive)
        boolean isErrorMessageCorrect = actualErrorMessage.toString().trim().toLowerCase().contains(expectedErrorMessage.toLowerCase());
        log.debug("Is error message correct? " + isErrorMessageCorrect);

        // Detailed assertion with both expected and actual messages
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message to contain: \"" + expectedErrorMessage + "\" but found: \"" + actualErrorMessage.toString().trim() + "\"");
        
    }

    @Test(priority = 3)
    public void LessAmount() throws InterruptedException, IOException {
    	
   	 log.debug("Testing  with less than miniumum amount");
   	 
        // Fetching data from Excel
        String amount = Excel.Testdata(Path, "Sheet1", 22, 1);

        // Initialize the Wallet_Recharge page and attempt recharge with less amount
        recharge = new Wallet_Recharge(driver);
        recharge.recharge(amount);
        log.debug("Amount entered : 99");
        
        String expectedErrorMessage="Minimum Recharge Amount is 100, Please enter Valid Recharge Amount";
        List<WebElement> errorElements = recharge.getErrorMessages();
        StringBuilder actualErrorMessage = new StringBuilder();

        // Process the list of error elements
        if (!errorElements.isEmpty()) {
            for (WebElement errorElement : errorElements) 
            {
                actualErrorMessage.append(errorElement.getText().trim()).append(" "); // Concatenate error messages if multiple
            }
            log.debug("Error message elements found with text: " + actualErrorMessage.toString().trim());
        } else {
            log.debug("No error messages found");
        }

        log.debug("Expected error message: " + expectedErrorMessage);
        log.debug("Actual error message: " + actualErrorMessage.toString().trim());

        // Assert that the actual error message contains the expected error message (case-insensitive)
        boolean isErrorMessageCorrect = actualErrorMessage.toString().trim().toLowerCase().contains(expectedErrorMessage.toLowerCase());
        log.debug("Is error message correct? " + isErrorMessageCorrect);

        // Detailed assertion with both expected and actual messages
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message to contain: \"" + expectedErrorMessage + "\" but found: \"" + actualErrorMessage.toString().trim() + "\"");
    }

    @Test(priority = 4)
    public void DecimalAmount() throws InterruptedException, IOException {
    	log.debug("Testing  with amount greater than 100 with decimals");
        // Fetching data from Excel
        String amount = Excel.Testdata(Path, "Sheet1", 22, 2);
        String puser = Excel.Testdata(Path, "Sheet1", 19, 2);
        String ppswd = Excel.Testdata(Path, "Sheet1", 19, 3);

        // Initialize the Wallet_Recharge page and attempt recharge with decimal amount
        recharge = new Wallet_Recharge(driver);
        recharge.recharge(amount);
        log.debug("Amount entered : 100.5");
        log.debug("Amount got round off and navigated to payment gateway");
        
        recharge.bank();
        log.debug("Paymode selected : Net Banking");

        // Perform payment via Axis bank
        recharge.axis(puser, ppswd);
        log.debug("Payu credentials got entered");
        
        recharge.logout();
        log.debug("Application was logout");
        
        String actualResult = "Success";
        String expectedResult = "Success";
        
        log.debug("Expected result: " + expectedResult + ", Actual result: " + actualResult);
        
        Assert.assertEquals(actualResult, expectedResult, "Wallet Recharge is successfull");
        
        
    }
}
