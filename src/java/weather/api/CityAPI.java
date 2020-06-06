package weather.api;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import weather.configuration.APIConfig;
import weather.configuration.ResourceBundleManager;
import weather.entity.forecast.Forecast;
import weather.entity.geo.GEO;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class CityAPI {
    private static CityAPI cityAPI;
    private ResourceBundleManager resourceBundleManager;

    private CityAPI(){
        resourceBundleManager = ResourceBundleManager.getInstance();
    }

    public static CityAPI getInstance(){
        if(cityAPI == null){
            cityAPI = new CityAPI();
        }
        return cityAPI;
    }

    public String getExternalIP()  {
        String ip = null;
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            ip = scanner.nextLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    resourceBundleManager.getString("locationError"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return ip;
    }

    public GEO getCityByLocation() {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get(String.format(APIConfig.GET_CITY, getExternalIP()))
                    .header("x-rapidapi-host", "ip-geo-location.p.rapidapi.com")
                    .header("x-rapidapi-key", APIConfig.CITY_API)
                    .asString();
        } catch (UnirestException e) {
            JOptionPane.showMessageDialog(
                    null,
                    resourceBundleManager.getString("cityAPIError"),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(response.getBody());
        GEO geo = gson.fromJson(jsonObject.toString(), GEO.class);
        Double lat = geo.getLocation().getLatitude();
        Double lon = geo.getLocation().getLongitude();
        try {
            geo.setForecast(getForecastByLocation(lat, lon));
        } catch (UnirestException e) {
            return null;
        }
        return gson.fromJson(jsonObject.toString(), GEO.class);
    }

    public Forecast getForecastByLocation(Double lat, Double lon) throws UnirestException {
        HttpResponse<String> response = Unirest.get(String.format(APIConfig.GET_FORECAST, lat, lon))
                .header("x-rapidapi-host", "dark-sky.p.rapidapi.com")
                .header("x-rapidapi-key", APIConfig.CITY_API)
                .asString();
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(response.getBody());
        return gson.fromJson(jsonObject.toString(),
                Forecast.class);
    }
}
