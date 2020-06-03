package weather.configuration;

import java.util.Map;

public class APIConfig {
    public static String GET_CITY = "https://ip-geo-location.p.rapidapi.com/ip/%s?format=json";
    public static String CITY_API;

    static {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();
        CITY_API = env.get("CITY_API");
    }
}
