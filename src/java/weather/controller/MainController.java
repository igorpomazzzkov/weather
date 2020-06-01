package weather.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import weather.configuration.PathConfig;

public class MainController {

    private String pathToCss = PathConfig.cssPath + "main.css";

    @FXML
    private Button changeCity;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    public void setCity(){
        this.cityLabel.setText("Oshmyany");
        this.countryLabel.setText("Belarus");
    }

    public String getPathToCss() {
        return pathToCss;
    }
}
