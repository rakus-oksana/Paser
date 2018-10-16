import lombok.Getter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


@ToString
@Getter
public class Coordinates {
    private String coordinates;

    public void setCoordinates(String city_url) throws IOException {
        try {
            Document doc = Jsoup.connect(city_url).get();
            Elements coords = doc.select(".geo");
            this.coordinates = coords.first().text();
        } catch (NullPointerException e) {
            this.coordinates = null;
        }
    }
}
