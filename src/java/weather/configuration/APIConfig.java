package weather.configuration;

import java.util.Map;

public class APIConfig {
    public static String GET_CITY = "https://ip-geo-location.p.rapidapi.com/ip/%s?format=json";
    public static String GET_FORECAST = "https://dark-sky.p.rapidapi.com/%f,%f?units=si";
    public static String CITY_API;
    public static String SEARCH_CITY_API;

    static {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();
        CITY_API = env.get("CITY_API");
        SEARCH_CITY_API = env.get("SEARCH_CITY");
    }
}
