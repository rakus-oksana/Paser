import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Victor on 03.10.2018; edited by Oksana Rakus
 */
public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть місто України: ");
        String cityName = sc.nextLine().toLowerCase();
        String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)";
        Document doc = Jsoup.connect(url).get();
        Elements cities = doc.select("table tr");
        System.out.println("Пошук міста...");

        for (Element city : cities) {
            City myCity = City.parse(city);
            if (myCity != null) {
                if (myCity.getName().toLowerCase().startsWith(cityName)) {
                    System.out.println(myCity);
                    String forecast = new WeatherForecaster().forecast(myCity);
                    System.out.println(forecast);
                    System.exit(0);
                }
            }
        }
        System.out.println("Вибачте, міста немає в списку.");

    }

}
