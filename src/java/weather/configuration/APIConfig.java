package weather.configuration;

import java.util.Map;

public class APIConfig {
    public static String GET_PLACE = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input='%s'&inputtype=textquery&fields=photos,name&key=";
    private static String API_KEY;

    static {
        ProcessBuilder processBuilder = new ProcessBuilder();
        Map<String, String> env = processBuilder.environment();
        API_KEY = env.get("PLACE_API_KEY");
        GET_PLACE = GET_PLACE + API_KEY;
    }
}
