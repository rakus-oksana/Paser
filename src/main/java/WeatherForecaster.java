import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;


public class WeatherForecaster {


    public String forecast(City city) throws UnirestException{
        final String URL = "https://api.apixu.com/v1/current.json";
        final String KEY ="b7ec06fd7af446cba4e92321181610";

        if (city != null) {
            if (city.getCoordinates() != null) {
                HttpResponse<JsonNode> response = Unirest.post(URL)
                                                  .queryString("key", KEY)
                                                  .queryString("q", city.getCoordinates().toString())
                                                  .asJson();
                JSONObject current = response.getBody().getObject().getJSONObject("current");
                String weather;
                weather = String.format("Погода: %s", current.getJSONObject("condition").getString("text"));
                weather += String.format("\nТемпература °C: %s (real-feel: %s)", current.getDouble("temp_c"), current.getDouble("feelslike_c"));
                weather += String.format("\nВологість: %s", current.getDouble("humidity"));
                weather += String.format("\nВітер: %s %s м/год", current.getString("wind_dir"), current.getDouble("wind_mph"));
                weather += String.format("\nОстаннє оновлення: %s", current.getString("last_updated"));
//                System.out.println(current);
                return weather;
            } else {
                return "Вибачте, дані про погоду недоступні для вибраного міста.";
            }
        }
        return null;
    }

}
