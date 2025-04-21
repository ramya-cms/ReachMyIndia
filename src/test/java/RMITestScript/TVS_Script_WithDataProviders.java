package RMITestScript;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import Generic.BaseTest;
import Generic.TVS_DataProvider;
import POM_Classes.Login;
import POM_Classes.TVS_Service;

public class TVS_Script_WithDataProviders extends BaseTest {

    private WebDriver driver;
    private Login loginPage;
    private TVS_Service tser;

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = getDriver(); // Initialize WebDriver
        loginPage = new Login(driver);
        
        // Perform login
        loginPage.login("KAKCB0003", "Password@1");
        
        // Verify login success
        boolean loginSuccess = loginPage.isLoginSuccessful(); // Ensure this method exists in your Login class
        Assert.assertTrue(loginSuccess, "Login was unsuccessful! Please check credentials or application state.");
  
        tser = new TVS_Service(driver);
    }

    @Test(dataProvider = "excelData", dataProviderClass = TVS_DataProvider.class)
    public void testValidDetails(String fname, String phone, String mobile, String dob, String address,String state,String dist,String taluk,
    		String pincode, String prod, String atrack,String dtype,String path) throws InterruptedException, IOException {
        // Fill in the form and submit
        tser.details(fname, phone, mobile, dob, address, state, dist, taluk, pincode, prod, atrack, dtype, path);

        // Navigate to services and perform actions
        tser.services();

        // Assert that the form submission was successful
        //String successMessage = tser.getSuccessMessage(); // Assuming `getSuccessMessage` method exists in `TVS_Service`
       // Assert.assertTrue(successMessage.contains("Transaction successful"), "Form submission failed or success message is incorrect!");
    }

    @AfterTest
    public void logoutAfterTest() throws InterruptedException {
        // Logout from the application
        tser.logout();
        Thread.sleep(2000);
        // Assert successful logout
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"), "Logout was unsuccessful!");
    }
}
