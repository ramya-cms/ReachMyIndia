//This code is used if tried to fetch from DB

package RMITestScript;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic.BaseTest;
import Generic.CredentailsManager;
import POM_Classes.EmailTestPage;

public class CredentialsScript extends BaseTest {
    private static String capturedUsername;
    private static String plainTextPassword;
    private Connection connection;
    private EmailTestPage emailTestPage;
    
    @BeforeTest
    public void setup() throws IOException, SQLException, ClassNotFoundException {
        initializeDatabaseConnection();
        fetchCredentialsFromDatabase();
        CredentailsManager.setUsername(capturedUsername);
        CredentailsManager.setPassword(plainTextPassword);
    }

    private void initializeDatabaseConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://10.9.246.163;databaseName=RMI;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "$evKum#10361";
        connection = DriverManager.getConnection(url, username, password);
    }

    private void fetchCredentialsFromDatabase() throws SQLException {
        String query = "SELECT TOP 1 Username, Password FROM CMN_MASTER_USERS WHERE EMAILID='test@9vekviur.mailosaur.net' ORDER BY CreatedDate DESC";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            capturedUsername = resultSet.getString("Username");
            plainTextPassword = resultSet.getString("Password");

            System.out.println("Captured Username from DB: " + capturedUsername);
            System.out.println("Captured Plain Text Password from DB: " + plainTextPassword);
        }
    }

    @Test(priority = 5)
    public void testRegistration() throws InterruptedException {
        driver = getDriver();
        Thread.sleep(3000);

        System.out.println("Captured Username: " + CredentailsManager.getUsername());
        System.out.println("Captured Plain Text Password: " + CredentailsManager.getPassword());

        emailTestPage = new EmailTestPage(driver);
        emailTestPage.login(CredentailsManager.getUsername(), CredentailsManager.getPassword());
    }
}
