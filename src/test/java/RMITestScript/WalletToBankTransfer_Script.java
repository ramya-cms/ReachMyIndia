package RMITestScript;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.Auto_Constant;
import Generic.BaseTest;
import Generic.Excel;
import POM_Classes.Login;
import POM_Classes.OPLogin;
import POM_Classes.WalletToBank;
import POM_Classes.WalletToBank_CACC;
import POM_Classes.WalletToBank_SSM;

public class WalletToBankTransfer_Script extends BaseTest implements Auto_Constant {
    
    private Login loginPage;
    private WalletToBank wallet;
    private Connection connection;
    
    private String mOtp;
    private String eOtp;
    
    private WalletToBank_SSM smverify;
    WebDriverWait wait;

    // Static values for mobile number and email ID
    private static final String MOBILE_NUMBER = "9738526155"; 
    private static final String EMAIL_ID = "testing678@yahoo.co.in"; 

    @BeforeTest
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        initializeDatabaseConnection();
    }

    @Test(priority = 1)
    public void wtb() throws InterruptedException {
    	
    	
        driver = getDriver(); 

        // Fetch data from Excel
        String username = Excel.Testdata(Path, "Sheet1", 0, 0);
        String password = Excel.Testdata(Path, "Sheet1", 0, 1);

        loginPage = new Login(driver);
        Thread.sleep(2000);
        loginPage.login(username, password);

        // Fetch remaining test data
        String rem = Excel.Testdata(Path, "Sheet1", 19, 5);
        String tamt = Excel.Testdata(Path, "Sheet1", 19, 6);

        wallet = new WalletToBank(driver,connection);
        wallet.request(rem);
        Thread.sleep(2000);
        
        
        // Fetch OTPs from database
        mOtp = waitForNewOtp("Sms", MOBILE_NUMBER);
        eOtp = waitForNewOtp("Email", EMAIL_ID);
        
        // Ensure the OTP fields are cleared before entering new OTP
        wallet.clearOtpFields();
        Thread.sleep(1000);
        
        wallet.enterMobileOtp();
        Thread.sleep(2000);
        
        wallet.enterEmailOtp();
        
        wallet.amount(tamt);
    }

    private void initializeDatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://10.9.246.163;databaseName=RMI;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "$evKum#10361";
        connection = DriverManager.getConnection(url, username, password);
    }

    private String waitForNewOtp(String sendType, String sendTo) throws InterruptedException {
        String otp = null;
        int maxRetries = 10; 
        int retryInterval = 3000; 

        for (int i = 0; i < maxRetries; i++) {
            otp = getOtpFromDatabase("CMN_LOG_OTP", sendType, sendTo);
            if (otp != null && !otp.isEmpty()) {
                System.out.println("Latest OTP retrieved: " + otp);
                return otp;
            }
            System.out.println("Waiting for new OTP...");
            Thread.sleep(retryInterval);
        }

        return otp;
    }

    private String getOtpFromDatabase(String tableName, String sendType, String sendTo) {
        String otp = null;
        String query = "SELECT TOP 1 OTP FROM " + tableName + 
                       " WHERE SENDTYPE='" + sendType + "' AND SENDTO='" + sendTo + "' " +
                       " ORDER BY CREATEDDATE DESC";

        System.out.println("Executing query: " + query);

        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("Database connection is null or closed. Reconnecting...");
                reconnectDatabase();
            }

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (resultSet.next()) {
                    otp = resultSet.getString("OTP");
                    System.out.println("Fetched OTP: " + otp);
                } else {
                    System.out.println("No OTP found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception while retrieving OTP: " + e.getMessage());
            e.printStackTrace();
        }

        return otp;
    }

    private void reconnectDatabase() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            String url = "jdbc:sqlserver://10.9.246.163;databaseName=RMI;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "$evKum#10361";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database reconnected successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to reconnect to database: " + e.getMessage());
        }
    }

    
    @AfterTest
    public void closeDatabaseConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }
   
  @Test(priority=2)
    public void approval() throws InterruptedException
    {
    	driver = getDriver();
    
    	 // Fetch data from Excel
        String username = Excel.Testdata(Path, "Sheet1", 3, 6);
        String password = Excel.Testdata(Path, "Sheet1", 3, 7);

        loginPage = new Login(driver);
        Thread.sleep(2000);
        loginPage.login(username, password);
        
        
        String srch = Excel.Testdata(Path, "Sheet1", 4, 6);
        String com = Excel.Testdata(Path, "Sheet1", 4, 5); 
        
        smverify = new WalletToBank_SSM(driver);
        
        smverify.activity();
        
        smverify.approval(srch,com);
        
        
    }
    
    @Test(priority=3)
    public void settlement() throws InterruptedException
    {
    		
    	driver = getDriver();
        
   	 // Fetch data from Excel
       String username = Excel.Testdata(Path, "Sheet1", 15, 6);
       String password = Excel.Testdata(Path, "Sheet1", 16, 6);

       loginPage = new Login(driver);
       Thread.sleep(2000);
       loginPage.login(username, password);
       
       String dat = Excel.Testdata(Path, "Sheet1", 24, 5);
       
       String stas = Excel.Testdata(Path, "Sheet1", 24, 6);
       
       String srch = Excel.Testdata(Path, "Sheet1", 4, 6);
       
       String stas1 = Excel.Testdata(Path, "Sheet1", 24, 2);
       
       String refdat = Excel.Testdata(Path, "Sheet1", 24, 3);
       
       String ref_no = Excel.Testdata(Path, "Sheet1", 27, 5);
       
       String remk = Excel.Testdata(Path, "Sheet1", 27, 6);
       
       
       WalletToBank_CACC settle = new WalletToBank_CACC(driver);
       
       Thread.sleep(2000);
       
       settle.settlement(dat, stas, srch, stas1,refdat,ref_no, remk);
       
          	
    }
}

