package prove03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {
    public String readHTTP(String url) {
        try {
            // This will convert the String url to a URL object which will allow a connection to be established
            URL urlObj = new URL(url);

            // This will open a connection to the remote site
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();

            // An input stream to read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder data = new StringBuilder();
            String line;
            // Keeps reading from the remote server until we get null

            do {
                line = reader.readLine();
                if (line != null) {
                    data.append(line);
                }
            } while (line != null);

            return data.toString();
        }
        catch (IOException ioe) {
            // Print out error if something went wrong
            System.out.println("Error reading HTTP response: " + ioe);
            return null;
        }
    }
}
