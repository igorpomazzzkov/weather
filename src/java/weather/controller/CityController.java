package weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import weather.animation.Shake;
import weather.configuration.ResourceBundleManager;
import weather.configuration.Serialization;

import java.awt.event.KeyAdapter;

public class CityController extends KeyAdapter {

    private ResourceBundleManager resourceBundleManager;

    @FXML
    private TextField cityText;

    @FXML
    private Button changeCityButton;

    public CityController() {
        resourceBundleManager = ResourceBundleManager.getInstance();
    }

    @FXML
    public void initialize() {
        changeCityButton.setText(resourceBundleManager.getString("setCityButton"));
        cityText.setPromptText(resourceBundleManager.getString("changeCityTextBoxPlaceholder"));
        changeCityButton.setOnAction(event -> {
            setCity();
        });
    }

    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            setCity();
        }
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            this.cityText.getScene().getWindow().hide();
        }
    }

    private void setCity() {
        if (!cityText.getText().isBlank()) {
            Serialization serialization = new Serialization();
            this.cityText.getScene().getWindow().hide();
        } else {
            Shake shake = new Shake(this.cityText);
            shake.startShake();
            cityText.setStyle("-fx-effect: dropshadow(three-pass-box, #e86c6c, 5, 0, 0, 0);");
        }
    }

}
