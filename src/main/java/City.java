/**
 * Created by Victor on 03.10.2018.
 */
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Executors;


@Getter
@Setter
public class City {
    private String name;
    private String url;
    private String administrativeArea;
    private int numberOfCitizens;
    private String yearOfFound;
    private Coordinates coordinates; // Set this
    private double area;

    private static final int INFO_SIZE = 6;




    public static City parse(Element city) throws IOException {
        Elements info = city.select("td");
        if (info.size() == INFO_SIZE) {
            Element anchor = info.get(1).select("a").get(0);
            City myCity = new City();
            myCity.setName(anchor.attr("title"));
            myCity.setUrl(String.format("https://uk.wikipedia.org%s", anchor.attr("href")));
            myCity.setAdministrativeArea(info.get(2).text());
            myCity.setNumberOfCitizens(Integer.parseInt(info.get(3).text().replaceAll("[^0-9 ].*|\\s+","")));
            myCity.setYearOfFound(info.get(4).text());
            Coordinates coords = new Coordinates();
            coords.setCoordinates(myCity.getUrl());
            myCity.setCoordinates(coords);
            myCity.setArea(Double.parseDouble(info.get(5).text()));
            return myCity;
        }
        return null;
    }

    @Override
    public String toString() {
        return "\nМісто: " + name + '\n' +
                "URL:" + url + '\n' +
                "Область: " + administrativeArea + '\n' +
                "Чисельність населення: " + numberOfCitizens + '\n' +
                "Час заснування: " + yearOfFound + '\n' +
                "Координати: " + coordinates + '\n' +
                "Площа(км^2): " + area + '\n';
    }
}