package weather.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import weather.configuration.APIConfig;
import weather.entity.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class CityAPI {
    private static CityAPI cityAPI;

    private CityAPI(){}
    public static CityAPI getInstance(){
        if(cityAPI == null){
            cityAPI = new CityAPI();
        }
        return cityAPI;
    }

    public String getExternalIP(){
        String ip = null;
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
            ip = scanner.nextLine();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public GEO getCityByLocation() throws UnirestException, ParseException {
        HttpResponse<String> response = Unirest.get(String.format(APIConfig.GET_CITY, getExternalIP()))
                .header("x-rapidapi-host", "ip-geo-location.p.rapidapi.com")
                .header("x-rapidapi-key", APIConfig.CITY_API)
                .asString();
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject(response.getBody());
        return gson.fromJson(jsonObject.toString(), GEO.class);
    }

}
