package RMITestScript;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.Auto_Constant;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Registration;

public class Registration_Test extends BaseTest implements Auto_Constant {
    private Registration regis;
    private Connection connection;

    @BeforeTest
    public void setup() throws IOException, SQLException, ClassNotFoundException {
       // initialize(); // Initialize WebDriver and other necessary setup
        initializeDatabaseConnection(); // Initialize database connection
    }

    @Test(priority = 1)
    public void RegPage() throws InterruptedException, SQLException {
        driver = getDriver(); // Get the initialized WebDriver instance

        String Mbnum = Excel.Testdata(Path, "Sheet1", 4, 0);
        String EIDnum = Excel.Testdata(Path, "Sheet1", 4, 1);

        regis = new Registration(driver);

        // Start registration process
        regis.initiateRegistration();

        // Enter mobile number and wait for OTP
        regis.enterMobileNumber(Mbnum);
        String mOtp = waitForNewOtp("SMS", Mbnum);
        System.out.println("Fetched Mobile OTP: " + mOtp); // Debug line
        
        if (mOtp == null || mOtp.isEmpty()) {
            throw new IllegalArgumentException("Mobile OTP is null or empty.");
        }
        regis.enterMobileOtp(mOtp);

        // Enter email ID and wait for OTP
        regis.enterEmailId(EIDnum);
        String eOtp = waitForNewOtp("EMAIL", EIDnum);
        System.out.println("Fetched Email OTP: " + eOtp); // Debug line
        
        if (eOtp == null || eOtp.isEmpty()) {
            throw new IllegalArgumentException("Email OTP is null or empty.");
        }
        regis.enterEmailOtp(eOtp);

        // Complete registration
        regis.completeRegistration();

        Thread.sleep(1000); // Adjust sleep as needed
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
        int maxRetries = 20; // Increase the number of retries
        int retryInterval = 5000; // Increase the interval between retries to 5 seconds

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
        String query = "SELECT TOP 1 OTP FROM " + tableName + " WHERE SENDTYPE='" + sendType + "' AND SENDTO='" + sendTo + "' ORDER BY CREATEDDATE DESC";

        System.out.println("Executing query: " + query); // Debug line
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                otp = resultSet.getString("OTP");
                System.out.println("Retrieved " + sendType + " OTP: " + otp); // Log the retrieved OTP
            } else {
                System.out.println("No " + sendType + " OTP found in the database."); // Log if no OTP is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return otp;
    }

}
