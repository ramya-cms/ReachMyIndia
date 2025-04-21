package RMITestScript;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.MasterEmailTestPage;
import POM_Classes.Master_Payment;

public class MasterPaymentScript extends BaseTest 
{
	private Master_Payment mp;
	 private MasterEmailTestPage metp;
	 
	@Test(priority=4) 
	public void payment() throws InterruptedException, IOException
	{
			driver = getDriver(); 
	        Thread.sleep(3000);
	        
		String puser = Excel.Testdata(Path, "Sheet1", 19, 2);
        String ppswd = Excel.Testdata(Path, "Sheet1", 19, 3);
        String emailSub2 = Excel.Testdata(Path, "Sheet2", 8, 3);
  
        
        mp = new Master_Payment(driver);
     
        Thread.sleep(2000);
        mp.bank();
        Thread.sleep(2000);
        mp.axis(puser, ppswd);
        Thread.sleep(2000);
        
        metp = new MasterEmailTestPage(driver);
        Thread.sleep(4000);
 
        String emailBody = metp.readEmailandgetbody(emailSub2);
        System.out.println("Email Body: " + emailBody);
        
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
	
