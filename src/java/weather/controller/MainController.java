package weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import weather.api.CityAPI;
import weather.configuration.PathConfig;
import weather.configuration.ResourceBundleManager;
import weather.entity.geo.GEO;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    private GEO geo;
    private ResourceBundleManager resourceBundleManager;
    private final String C = "°";
    private final String imagePath = PathConfig.imagePath + "%s.png";


    @FXML
    private Button changeCity;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label temperatureLabel;

    @FXML
    private Label summaryLabel;

    @FXML
    private Label feelsLikeLabel;

    @FXML
    private ImageView imageView;

    @FXML
    private Label precipInstace;

    @FXML
    private Label humidity;

    @FXML
    private Label windSpeed;

    @FXML
    private Label windGust;

    public MainController() {
        resourceBundleManager = ResourceBundleManager.getInstance();
        CityAPI c = CityAPI.getInstance();
        geo = c.getCityByLocation();
        if (geo != null) {
            geo.addObserver(this);
        }
    }

    @FXML
    public void setCity() {
        try {
            URL cssFilePath = Paths.get(PathConfig.cssPath + "city.css").toUri().toURL();
            URL fxmlFilePath = Paths.get(PathConfig.fxmlPath + "city.fxml").toUri().toURL();

            Parent loader = FXMLLoader.load(fxmlFilePath);
            Scene scene = new Scene(loader);
            scene.getStylesheets().add(cssFilePath.toString());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Enter city");
            stage.setScene(scene);

            stage.initOwner(this.changeCity.getScene().getWindow());
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    @FXML
    public void initialize() throws MalformedURLException {
        if (geo != null) {
            initComponent();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Error of get data",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.cityLabel.setText("City");
            this.countryLabel.setText("Continent/Country/Region");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GEO geo = (GEO) arg;
        this.cityLabel.setText(geo.getCity().getName());
        this.countryLabel.setText(setCountryLabel(geo));
    }

    public String setCountryLabel(GEO geo) {
        String format = "%s, %s(%s), %s";
        String continent = geo.getContinent().getName();
        String country = geo.getCountry().getName();
        String countryCode = geo.getCountry().getCode();
        String area = geo.getArea().getName();
        return String.format(
                format,
                firstLetterToUpperCase(continent),
                firstLetterToUpperCase(country),
                countryCode.toUpperCase(),
                firstLetterToUpperCase(area));
    }

    public String firstLetterToUpperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public void initComponent() {
        this.cityLabel.setText(this.geo.getCity().getName());
        this.countryLabel.setText(this.setCountryLabel(this.geo));

        String temp = this.geo.getForecast().getCurrently().getTemperature() + this.C;
        String sum = this.geo.getForecast().getCurrently().getSummary();
        String feels = resourceBundleManager.getString("feelsLike") + " " +
                this.geo.getForecast().getCurrently().getApparentTemperature() + C;
        String precip = resourceBundleManager.getString("visibility") + " " +
                this.geo.getForecast().getCurrently().getVisibility() + " " +
                resourceBundleManager.getString("windSpeedSI");
        String humidity = resourceBundleManager.getString("humidity") + " " +
                this.geo.getForecast().getCurrently().getHumidity();
        String windSpeed = resourceBundleManager.getString("windSpeed") + " " +
                this.geo.getForecast().getCurrently().getWindSpeed() + " " +
                resourceBundleManager.getString("windSpeedSI");
        String windGust = resourceBundleManager.getString("windGust") + " " +
                this.geo.getForecast().getCurrently().getWindGust() + " " +
                resourceBundleManager.getString("windSpeedSI");

        this.changeCity.setText(resourceBundleManager.getString("changeCityButton"));
        this.temperatureLabel.setText(temp);
        this.summaryLabel.setText(sum);
        this.feelsLikeLabel.setText(feels);
        this.precipInstace.setText(precip);
        this.humidity.setText(humidity);
        this.windSpeed.setText(windSpeed);
        this.windGust.setText(windGust);

        File file = new File(String.format(imagePath, geo.getForecast().getCurrently().getIcon()));
        Image image = null;
        try {
            image = new Image(file.toURI().toURL().toString(), true);
            imageView.setImage(image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateData(){
        CityAPI cityAPI = CityAPI.getInstance();
        this.geo = cityAPI.getCityByLocation();
        initComponent();
        System.out.println("Refresh");
    }
}
