package weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import weather.configuration.Serialization;
import weather.entity.City;

import java.awt.event.KeyAdapter;

public class CityController extends KeyAdapter {
    @FXML
    private TextField cityText;

    @FXML
    private Button changeCityButton;


    @FXML
    public void initialize(){
        changeCityButton.setOnAction(event -> {
            setCity();
        });
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER){
            setCity();
        }
        if(keyEvent.getCode() == KeyCode.ESCAPE){
            this.cityText.getScene().getWindow().hide();
        }
    }

    private void setCity(){
        City city = City.getInstance();
        System.out.println(city.toString());
        if(!cityText.getText().isBlank()){
            city.setCity(cityText.getText());
            city.setCountry("Belarus");
            city.setContinent("Europe");
            city.notifyObservers();
            Serialization serialization = new Serialization();
            serialization.objectSerializable(city);
            this.cityText.getScene().getWindow().hide();
        }
    }

}
