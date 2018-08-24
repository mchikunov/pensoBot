package util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SendGetRequestToBot {

    public static String executeGetRequest(String targetUrl, String urlParameters) throws MalformedURLException {
       URL url = new URL(targetUrl);
       HttpURLConnection connection = null;

        try {
            //Create connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);
            connection.setDoInput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return null;
    }

}
