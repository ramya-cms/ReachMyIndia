//This code is used if tried to fetch from DB

package RMITestScript;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.CredentailsManager;
import POM_Classes.Login;

public class CapturedCredentials extends BaseTest {

    private Login loginPage;

    @Test(priority = 6)
    public void testLogin() throws Exception {
        Properties props = new Properties();
        String path = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/Config.properties";
        FileInputStream input = new FileInputStream(path);
        props.load(input);
        String url = props.getProperty("url");

        driver.get(url);

        // Capture generated credentials from CredentialsScript
        String username = CredentailsManager.getUsername();
        String password = "";//CredentailsManager.getPassword();

        // Ensure credentials are captured before proceeding
        if (username == null || password == null) {
            throw new Exception("Credentials not generated or not accessible.");
        }

        // Initialize the Login page with the driver
        loginPage = new Login(driver);
        Thread.sleep(2000);

        // Perform login
        loginPage.login(username, password);
        Thread.sleep(3000);

        // Add assertions to verify successful login
    }
}
