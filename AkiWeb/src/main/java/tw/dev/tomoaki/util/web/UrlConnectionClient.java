package tw.dev.tomoaki.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionClient {

    public String getContent(String designatedUrl) throws MalformedURLException, IOException { 
        URL url = new URL(designatedUrl);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }
}
