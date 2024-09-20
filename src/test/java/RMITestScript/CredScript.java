package RMITestScript;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.LoginTestCase;
import POM_Classes.SetPassword;

public class CredScript extends BaseTest {

    @Test(priority = 5)
    public void LoginToApplication() throws Exception {
        driver = getDriver();

        Properties props = new Properties();
        String path = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/Config.properties";
        FileInputStream input = new FileInputStream(path);
        props.load(input);
        String loginUrl = props.getProperty("loginUrl");
    

        // Read the username and password from the file
        String[] credentials = readCredentialsFromFile();
        String username = credentials[0];
        String password = credentials[1];

        if (username == null || password == null) {
            throw new RuntimeException("Credentials are missing from the file.");
        }

        // Log in to the application using the extracted credentials
        driver.get(loginUrl);
        LoginTestCase loginPage = new LoginTestCase(driver);
        loginPage.login(username, password);

        // Clear the file contents after use
        clearCredentialsFile();
        
        
     String password1 = Excel.Testdata(Path, "Sheet1", 0, 1);
        
        SetPassword spwd = new SetPassword(driver);
        spwd.password(password1, password1);
         
          Thread.sleep(2000);
         LoginTestCase newlogin = new LoginTestCase(driver);
         newlogin.login(username, password1);

    }

    private String[] readCredentialsFromFile() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/credentials.txt";
        String[] credentials = new String[2];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            if (line1 != null && line2 != null) {
                credentials[0] = extractDetail(line1);
                credentials[1] = extractDetail(line2);
            } else {
                System.out.println("File is empty or has incorrect format.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    private String extractDetail(String line) {
        if (line != null && line.contains(": ")) {
            return line.split(": ")[1];
        }
        return null;
    }

    private void clearCredentialsFile() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/credentials.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(""); // Clear file contents
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
