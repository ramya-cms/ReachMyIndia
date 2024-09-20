package RMITestScript;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.LoginTestCase;
import POM_Classes.Services;
import org.openqa.selenium.JavascriptExecutor;


public class LoginScript extends BaseTest {
    private static Logger log;
    WebDriver driver;
    WebDriverWait wait;
    private LoginTestCase loginPage;

    @BeforeTest
    public void setup() throws IOException {
        log = LogManager.getLogger(LoginScript.class.getName());
        driver = getDriver();
        driver.get(getLoginUrl());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void testValidLogin() throws IOException, InterruptedException {
        log.debug("Testing valid login scenario");

        // Read test data from Excel
        String username = Excel.Testdata(Path, "Sheet1", 0, 0);
        String password = Excel.Testdata(Path, "Sheet1", 0, 1);

        // Initialize LoginTestCase and perform login
        loginPage = new LoginTestCase(driver);
        loginPage.login(username, password);
        log.debug("Username and Password entered");

        // Initialize Services page
        Services servicesPage = new Services(driver);

        // Default to "Failure" until we verify successful login
        String actualResult = "Failure";
        try {
            WebElement serviceElement = wait.until(ExpectedConditions.elementToBeClickable(servicesPage.Serv()));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", serviceElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", serviceElement);

            // Optionally wait for a confirmation that login is successful
            WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOf(servicesPage.Serv()));
            if (confirmationElement != null && confirmationElement.isDisplayed()) {
                log.debug("User successfully logged in, confirmation element displayed.");
                actualResult = "Success";
            }
        } catch (Exception e) {
            log.debug("Exception during login verification: ", e);
            // Capture page source for debugging
            String pageSource = driver.getPageSource();
            log.error("Page source during login verification: " + pageSource);
        }

        // Compare expected and actual results
        String expectedResult = "Success";
        log.debug("Expected result: " + expectedResult + ", Actual result: " + actualResult);
        Assert.assertEquals(actualResult, expectedResult, "Login result matched");
    }




    @Test(priority = 2)
    public void testInvalidUsername() throws InterruptedException {
        log.debug("Testing invalid username scenario");

        // Fetch invalid credentials from Excel
        String username = Excel.Testdata(Path, "Sheet1", 0, 3);
        String password = Excel.Testdata(Path, "Sheet1", 0, 4);

        // Initialize LoginTestCase and attempt login with invalid credentials
        loginPage = new LoginTestCase(driver);
        loginPage.login(username, password);

        // Get the list of error messages
        List<WebElement> errorElements = loginPage.getErrorMessages();
        StringBuilder actualErrorMessage = new StringBuilder();

        // Process the list of error elements
        if (!errorElements.isEmpty()) {
            for (WebElement errorElement : errorElements) {
                actualErrorMessage.append(errorElement.getText().trim()).append(" "); // Concatenate error messages if multiple
            }
            log.debug("Error message elements found with text: " + actualErrorMessage.toString().trim());
        } else {
            log.debug("No error messages found");
        }

        // Define the expected error message
        String expectedErrorMessage = "Invalid Credentials";

        // Log the expected and actual error messages for debugging
        log.debug("Expected error message: " + expectedErrorMessage);
        log.debug("Actual error message: " + actualErrorMessage.toString().trim());

        // Assert that the actual error message contains the expected error message (case-insensitive)
        boolean isErrorMessageCorrect = actualErrorMessage.toString().trim().toLowerCase().contains(expectedErrorMessage.toLowerCase());
        log.debug("Is error message correct? " + isErrorMessageCorrect);

        // Detailed assertion with both expected and actual messages
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message to contain: \"" + expectedErrorMessage + "\" but found: \"" + actualErrorMessage.toString().trim() + "\"");
       
    }

    @Test(priority = 3)
    public void testInvalidPassword() throws InterruptedException {
        log.debug("Testing invalid password scenario");

        // Fetch invalid credentials from Excel
        String username = Excel.Testdata(Path, "Sheet1", 0, 6);
        String password = Excel.Testdata(Path, "Sheet1", 0, 7);

        // Initialize LoginTestCase and attempt login with invalid credentials
        loginPage = new LoginTestCase(driver);
        loginPage.login(username, password);

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Get the list of error messages
        List<WebElement> errorElements = loginPage.getErrorMessages();
        StringBuilder actualErrorMessage = new StringBuilder();

        // Process the list of error elements
        if (!errorElements.isEmpty()) {
            for (WebElement errorElement : errorElements) {
                actualErrorMessage.append(errorElement.getText().trim()).append(" ");
            }
            log.debug("Error message elements found with text: " + actualErrorMessage.toString().trim());
        } else {
            log.debug("No error messages found");
        }

        // Define the expected error message
        String expectedErrorMessage = "username or password invalid";

        // Log the expected and actual error messages for debugging
        log.debug("Expected error message: " + expectedErrorMessage);
        log.debug("Actual error message: " + actualErrorMessage.toString().trim());

        // Assert that the actual error message contains the expected error message (case-insensitive)
        boolean isErrorMessageCorrect = actualErrorMessage.toString().trim().toLowerCase().contains(expectedErrorMessage.toLowerCase());
        log.debug("Is error message correct? " + isErrorMessageCorrect);

        // Detailed assertion with both expected and actual messages
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message to contain: \"" + expectedErrorMessage + "\" but found: \"" + actualErrorMessage.toString().trim() + "\"");
    }

    
    @Test(priority = 4)
    public void testInvalidUserPass() throws InterruptedException {
        log.debug("Testing invalid username and invalid password scenario");

        // Fetch invalid credentials from Excel
        String username = Excel.Testdata(Path, "Sheet1", 3, 3);
        String password = Excel.Testdata(Path, "Sheet1", 3, 4);

        // Initialize LoginTestCase and attempt login with invalid credentials
        loginPage = new LoginTestCase(driver);
        loginPage.login(username, password);

        // Get the list of error messages
        List<WebElement> errorElements = loginPage.getErrorMessages();
        StringBuilder actualErrorMessage = new StringBuilder();

        // Process the list of error elements
        if (!errorElements.isEmpty()) {
            for (WebElement errorElement : errorElements) {
                actualErrorMessage.append(errorElement.getText().trim()).append(" "); // Concatenate error messages if multiple
            }
            log.debug("Error message elements found with text: " + actualErrorMessage.toString().trim());
        } else {
            log.debug("No error messages found");
        }

        // Define the expected error message
        String expectedErrorMessage = "Invalid Credentials";

        // Log the expected and actual error messages for debugging
        log.debug("Expected error message: " + expectedErrorMessage);
        log.debug("Actual error message: " + actualErrorMessage.toString().trim());

        // Assert that the actual error message contains the expected error message (case-insensitive)
        boolean isErrorMessageCorrect = actualErrorMessage.toString().trim().toLowerCase().contains(expectedErrorMessage.toLowerCase());
        log.debug("Is error message correct? " + isErrorMessageCorrect);

        // Detailed assertion with both expected and actual messages
        Assert.assertTrue(isErrorMessageCorrect, "Expected error message to contain: \"" + expectedErrorMessage + "\" but found: \"" + actualErrorMessage.toString().trim() + "\"");
    }
    
    @Test(priority = 5)
    public void testEmptyUsernameAndPassword() throws InterruptedException {
        log.debug("Testing empty username and password scenario");

        // Retrieve test data for empty username and password
        String username = Excel.Testdata(Path, "Sheet1", 0, 9);
        String password = Excel.Testdata(Path, "Sheet1", 0, 10);

        // Initialize the loginPage object
        LoginTestCase loginPage = new LoginTestCase(driver);

        // Perform login with empty username and password
        loginPage.login(username, password);

        String actualErrorMessage = "Please Enter UserName";

        try {
            // Wait for the error message element to be visible
            log.debug("Attempting to locate error message using xpath='//div[@id='UserName-invalid']'");
            WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='UserName-invalid']"))); // Adjust the locator if necessary
            actualErrorMessage = errorElement.getText().trim();
            log.debug("Error message element found with text: " + actualErrorMessage);
        } catch (Exception e) {
            // Capture page source for debugging
            String pageSource = driver.getPageSource();
            log.error("Error getting error message. Page source: " + pageSource, e);
            // Ensure the test fails if there's an issue locating the error message
            Assert.fail("Failed to get error message due to exception: " + e.getMessage());
        }

        // Expected error message when both fields are empty
        String expectedErrorMessage = "Please Enter UserName";
        log.debug("Expected error message: " + expectedErrorMessage + ", Actual error message: " + actualErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Unexpected error message for empty fields");
    }


    
    @BeforeMethod
    public void beforeMethod() {
        // Any setup that needs to run before each test method
    	//driver = getDriver();
       driver.get(getLoginUrl());
    }
    @AfterMethod
    public void afterMethod() {
        try {
            if (loginPage != null) {
                loginPage.logout();
            }
        } catch (Exception e) {
            log.error("Logout failed or session already terminated.", e);
  
}
    }
}