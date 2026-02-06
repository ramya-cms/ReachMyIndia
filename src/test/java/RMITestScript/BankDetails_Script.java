package RMITestScript;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.BankDetails;
import POM_Classes.Login;

public class BankDetails_Script extends BaseTest {

    private Login loginPage;
    private BankDetails bnk;
    private Connection connection;

    @BeforeTest
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        initializeDatabaseConnection();
    }

    @Test(priority = 1)
    public void bnkDetails() throws InterruptedException, SQLException {
        driver = getDriver();

        String username = Excel.Testdata(Path, "Sheet5", 5, 3);
        String password = Excel.Testdata(Path, "Sheet5", 5, 4);

        String name = Excel.Testdata(Path, "Sheet5", 2, 0);
        String acno = Excel.Testdata(Path, "Sheet5", 2, 1);
        String cacno = Excel.Testdata(Path, "Sheet5", 2, 2);
        String ifs = Excel.Testdata(Path, "Sheet5", 2, 3);
        String cifs = Excel.Testdata(Path, "Sheet5", 2, 4);
        String pan = Excel.Testdata(Path, "Sheet5", 2, 5);
        String cpan = Excel.Testdata(Path, "Sheet5", 2, 6);
        String upl = Excel.Testdata(Path, "Sheet5", 2, 7);
        String EIDnum = Excel.Testdata(Path, "Sheet5", 5, 1);

        loginPage = new Login(driver);
        loginPage.login(username, password);
        Thread.sleep(3000);

        bnk = new BankDetails(driver);
        bnk.bnkDtls(name, acno, cacno, ifs, cifs, pan, cpan);

        String eOtp = waitForNewOtp("EMAILPR", EIDnum);
        if (eOtp == null || eOtp.isEmpty()) {
            throw new IllegalArgumentException("Email OTP is null or empty.");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement otpField = driver.findElement(By.id("BankEmailOTP"));
        wait.until(driver -> otpField.isEnabled());

        otpField.clear();
        otpField.sendKeys(eOtp);
        System.out.println("OTP entered successfully: " + eOtp);

        File file = new File(upl);
        if (!file.exists()) {
            throw new RuntimeException("Upload file not found: " + upl);
        }
        bnk.upload(file.getAbsolutePath());

        bnk.cont();
    }

    private void initializeDatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://10.9.246.163;databaseName=RMI;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "$evKum#10361";
        connection = DriverManager.getConnection(url, username, password);
    }

    private String waitForNewOtp(String sendType, String sendTo) throws InterruptedException, SQLException {
        String otp = null;
        int maxRetries = 20;
        int retryInterval = 5000;

        for (int i = 0; i < maxRetries; i++) {
            otp = getOtpFromDatabase("CMN_LOG_OTP", sendType, sendTo);
            if (otp != null && !otp.isEmpty()) {
                break;
            }
            Thread.sleep(retryInterval);
        }

        return otp;
    }

    private String getOtpFromDatabase(String tableName, String sendType, String sendTo) {
        String otp = null;
        String query = "SELECT TOP 1 OTP FROM " + tableName + " WHERE SENDTYPE='" + sendType +
                       "' AND SENDTO='" + sendTo + "' ORDER BY CREATEDDATE DESC";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                otp = resultSet.getString("OTP");
                System.out.println("OTP retrieved: " + otp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return otp;
    }
}
