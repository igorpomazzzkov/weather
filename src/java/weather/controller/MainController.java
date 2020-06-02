package weather.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import weather.configuration.PathConfig;
import weather.configuration.Serialization;
import weather.entity.City;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class MainController implements Observer {

    @FXML
    private Button changeCity;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    public MainController() {
        Serialization serialization = new Serialization();
        City city = City.getInstance();
        City cityFromFile = (City) serialization.getObjectByDeserializable(city);
        city.setCity(cityFromFile.getName());
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
    public void initialize(){
        this.cityLabel.setText(City.getInstance().getName());
    }

    @Override
    public void update(Observable o, Object arg) {
        City city = (City) arg;
        this.cityLabel.setText(city.getName());
        this.countryLabel.setText(setCountryLabel(city.getCountry(), city.getContinent()));
    }

    public String setCountryLabel(String country, String continents){
        String format = "%s, %s";
        return String.format(format, firstLetterToUpperCase(country), firstLetterToUpperCase(continents));
    }

    public String firstLetterToUpperCase(String str){
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
