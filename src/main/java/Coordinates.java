import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


@Getter
public class Coordinates {
    private String lat;
    private String lon;

    public static Coordinates setting(String city_url) throws IOException {
        try {
            Document doc = Jsoup.connect(city_url).get();
            String[] coords = doc.select(".geo").first().text().split("; ");
            Coordinates coordinates = new Coordinates();
            coordinates.lat = coords[0];
            coordinates.lon = coords[0];
            return coordinates;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return lat + "," + lon;
    }
}
