package weather.configuration;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

public class APIConfig {
    public static String GET_CITY = "https://ip-geo-location.p.rapidapi.com/ip/%s?lang=%s&format=json";
    public static String GET_FORECAST = "https://dark-sky.p.rapidapi.com/%f,%f?lang=%s&units=si";
    public static String CITY_API;
    public static String SEARCH_CITY_API;
    public static final String C = "°";
    private static final String imagePath = PathConfig.imagePath + "%s.png";

    static {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();
        CITY_API = env.get("CITY_API");
        SEARCH_CITY_API = env.get("SEARCH_CITY");
    }

    public static Image getImage(String icon){
        File file = new File(String.format(imagePath, icon));
        Image image = null;
        try {
            return image = new Image(file.toURI().toURL().toString(), true);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
