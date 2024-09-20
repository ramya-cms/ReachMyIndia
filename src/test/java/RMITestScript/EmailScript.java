//EMail Script Backup

package RMITestScript;

import java.io.FileInputStream;
import java.util.Properties;


import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import Generic.MailosaurAPI;
import POM_Classes.EmailTestPage;


public class EmailScript extends BaseTest {
   
	private EmailTestPage EP;


    @Test(priority = 4)
    public void EmailVer() throws Exception {
    	
    	driver = getDriver();
    	
    	Properties props = new Properties();
    	 String path = System.getProperty("user.dir") + "/src/test/resources/ConfigFile/Config.properties";
    	FileInputStream input = new FileInputStream(path);
    	props.load(input);
    	String url = props.getProperty("googleUrl");
      //  prop = new Properties();
        
    	 driver.get(url);
    	 
        Thread.sleep(3000);
        
        String username = Excel.Testdata(Path, "Sheet2", 1, 0);
        String password = Excel.Testdata(Path, "Sheet2", 1, 1);
        String emailSub1 = Excel.Testdata(Path, "Sheet2", 1, 2);
        String emailSub2 = Excel.Testdata(Path, "Sheet2", 1, 3);

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Email Subject 1: " + emailSub1);
        System.out.println("Email Subject 2: " + emailSub2);

        MailosaurAPI mailService = new MailosaurAPI();

        String latestEmailContent;

        try {
            latestEmailContent = mailService.fetchLatestEmail();
        } catch (Exception e) {
            throw new Exception("Failed to fetch email: " + e.getMessage(), e);
        }

        if (latestEmailContent == null || latestEmailContent.isEmpty()) {
            throw new Exception("No email content fetched.");
        }

        //String extractedUsername = extractUsername(latestEmailContent);
        //String extractedPassword = extractPassword(latestEmailContent);

        //System.out.println("Extracted Username: " + extractedUsername);
        //System.out.println("Extracted Password: " + extractedPassword);

        EP = new EmailTestPage(driver);
        EP.login(username, password);
        EP.readEmail(emailSub1);
        
        // Add delay if needed
        Thread.sleep(5000); // Wait for 5 seconds

        EP.readEmailandgetbody(emailSub2);
    }

   
}