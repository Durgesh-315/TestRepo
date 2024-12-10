package dbService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FetchDataFromAPI {

    public static JsonObject fetchData(String apiUrl) {
        JsonObject jsonResponse = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                jsonResponse = JsonParser.parseString(content.toString()).getAsJsonObject();
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }
}
