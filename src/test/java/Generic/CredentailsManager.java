package Generic;



public class CredentailsManager {
    private static String Username;
    private static String Password;


    public static void setUsername(String user) {
        Username = user;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setPassword(String pass) {
        Password = pass;
    }

    public static String getPassword() {
        return Password;
    }
   
}
