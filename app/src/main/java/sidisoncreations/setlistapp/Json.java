package sidisoncreations.setlistapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This class is used to get JSON data back from an api call
 */
public class Json {

    public static JSONObject getJSON(String mUrl) {
        BufferedReader reader = null;
        String jsonText = "";

        try {
            URL url = new URL(mUrl);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            jsonText = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject data = null;
        try {
            data = new JSONObject(jsonText);
            if (reader != null) reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
