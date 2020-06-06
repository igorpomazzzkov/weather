package weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import weather.api.CityAPI;
import weather.configuration.PathConfig;
import weather.configuration.ResourceBundleManager;
import weather.entity.geo.GEO;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    private GEO geo;
    private ResourceBundleManager resourceBundleManager;


    @FXML
    private Button changeCity;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

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
    public void initialize() {
        this.changeCity.setText(resourceBundleManager.getString("changeCityButton"));
        if (geo != null) {
            this.cityLabel.setText(this.geo.getCity().getName());
            this.countryLabel.setText(this.setCountryLabel(this.geo));
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


}
