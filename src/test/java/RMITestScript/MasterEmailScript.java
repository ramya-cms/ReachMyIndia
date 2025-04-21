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
import POM_Classes.MasterEmailTestPage;
import POM_Classes.Master_Payment;

public class MasterEmailScript extends BaseTest {

    private MasterEmailTestPage EP;
    private Master_Payment mp;
    
    @Test(priority = 3)
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
        String emailSub1 = Excel.Testdata(Path, "Sheet2", 8, 2);
        
        // Displaying username and password from Excel in console
        System.out.println("Username from Excel: " + excelUsername);
        System.out.println("Password from Excel: " + excelPassword);

        EP = new MasterEmailTestPage(driver);
        EP.login(excelUsername, excelPassword);
        EP.readEmail(emailSub1);
        

    }
}
