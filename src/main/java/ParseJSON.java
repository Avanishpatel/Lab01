import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ParseJSON {

    public static JsonArray parseJSON() {

        InputStream stream = null;
        JsonReader jsonReader = null;

        URL url = null;
        try {
            url = new URL("https://bootcamp-training-files.cfapps.io/week1/week1-stocks.json");
        } catch (MalformedURLException e) {

            System.out.println("File not found.");
            e.printStackTrace();
        }
        try {
            stream = url.openStream();
        } catch (IOException e) {
            System.out.println("IO-Exception occur.");
            e.printStackTrace();
        }

        jsonReader = Json.createReader(stream);

        JsonArray jsonArray = jsonReader.readArray();

        return jsonArray;

    }
}
