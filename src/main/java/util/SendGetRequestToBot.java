package util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SendGetRequestToBot {

    public static void executeGetRequest(String targetUrl, String urlParameters) throws Exception {
//        String url = targetUrl+urlParameters.getBytes(StandardCharsets.UTF_8);
        String url = targetUrl+"?address=" + "москва".getBytes(StandardCharsets.US_ASCII);
//        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(url);
//        System.out.println(byteBuffer);
       // ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(myString)

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
    }

}
