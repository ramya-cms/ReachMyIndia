package Generic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Base64;



public class MailosaurAPI {
    private static final String API_KEY = "thJGcMxXzoRMU7io6AKolsbvGQVpjXPK";
    private static final String SERVER_ID = "0g4tnuyv";
    private static final String BASE_URL = "https://mailosaur.com/api";
    
    
//    private String getAuthorizationHeader() {
//        String auth = API_KEY + ":";
//        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
//    }

    public String fetchLatestEmail() throws Exception {
        String url = String.format("%s/messages?server=%s", BASE_URL, SERVER_ID);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        String auth = API_KEY + ":";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        con.setRequestProperty("Authorization", "Basic " + encodedAuth);

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            throw new Exception("GET request not worked");
        }
    }
  
   // private static final String API_KEY = "your_mailosaur_api_key"; // Replace with your actual API key


}