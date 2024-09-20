package RMITestScript;

import java.io.IOException;
import org.testng.annotations.Test;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Reg_Form;

public class Applicant_Details extends BaseTest {
    private Reg_Form appdtls;

    @Test(priority=2)
    public void form() throws InterruptedException, IOException {
        driver = getDriver(); // Get the initialized WebDriver instance
        
        String name = Excel.Testdata(Path, "Sheet1", 8, 0);
        String pan = Excel.Testdata(Path, "Sheet1", 8, 1);
        String dob = Excel.Testdata(Path, "Sheet1", 8, 2);
        String paddr = Excel.Testdata(Path, "Sheet1", 8, 3);
        String pcode = Excel.Testdata(Path, "Sheet1", 8, 4);
        Thread.sleep(1000);

        appdtls = new Reg_Form(driver); // Pass the driver instance to Reg_Form
        Thread.sleep(1000);
        appdtls.details(name, pan, dob, paddr, pcode);
        Thread.sleep(1000);

        // Use instance method to take a screenshot
        appdtls.screenshot();
    }
}
