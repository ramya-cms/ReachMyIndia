package RMITestScript;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.EmailTestPage;

public class EmailScriptNew extends BaseTest {

    private EmailTestPage EP;

    @Test(priority = 4)
    public void EmailVer() throws Exception {
        driver = getDriver();

        Properties props = new Properties();
        String path = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/Config.properties";
        FileInputStream input = new FileInputStream(path);
        props.load(input);
        String url = props.getProperty("googleUrl");

        driver.get(url);
        Thread.sleep(3000);

        // Fetch initial credentials from Excel
        String excelUsername = Excel.Testdata(Path, "Sheet2", 1, 0);
        String excelPassword = Excel.Testdata(Path, "Sheet2", 1, 1);
        String emailSub1 = Excel.Testdata(Path, "Sheet2", 1, 2);
        String emailSub2 = Excel.Testdata(Path, "Sheet2", 1, 3);

        // Displaying username and password from Excel in console
        System.out.println("Username from Excel: " + excelUsername);
        System.out.println("Password from Excel: " + excelPassword);

        EP = new EmailTestPage(driver);
        EP.login(excelUsername, excelPassword);
        EP.readEmail(emailSub1);

        // Add delay if needed
        Thread.sleep(5000); // Wait for 5 seconds

        String emailBody = EP.readEmailandgetbody(emailSub2);
        System.out.println("Email Body: " + emailBody);

        // Extract Username and Password from email body using regex
        String newUsername = extractDetailFromBody(emailBody, "Username");
        String newPassword = extractDetailFromBody(emailBody, "Password");

        System.out.println("Extracted Username from Email: " + newUsername);
        System.out.println("Extracted Password from Email: " + newPassword);

        // Save the new Username and Password to a file
        saveCredentialsToFile(newUsername, newPassword);
    }

    private String extractDetailFromBody(String emailBody, String key) {
        String regex = key + " : ([\\w]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailBody);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private void saveCredentialsToFile(String username, String password) {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/credentials.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Username: " + username);
            writer.newLine();
            writer.write("Password: " + password);
            System.out.println("Credentials saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
