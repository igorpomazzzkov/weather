package weather.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import weather.api.CityAPI;
import weather.configuration.APIConfig;
import weather.configuration.ResourceBundleManager;
import weather.entity.forecast.ForecastDate;
import weather.entity.geo.GEO;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class HourlyController implements Observer {

    @FXML
    private ScrollPane mainBox;

    @FXML
    private VBox vBox;

    private GEO geo;
    private ResourceBundleManager resourceBundleManager;

    public HourlyController() {
        resourceBundleManager = ResourceBundleManager.getInstance();
        CityAPI cityAPI = CityAPI.getInstance();
        cityAPI.addObserver(this);
        this.geo = cityAPI.getGeo();
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        this.mainBox = new ScrollPane(scrollBar);
    }

    public void initialize() {
        List<ForecastDate> hourly = geo.getForecast().getHourly().getData();
        hourly = hourly.stream().filter((date) ->
                date.getDateTime().getDay() == new Date().getDay()).collect(Collectors.toList());
        for(int i = 0; i < hourly.size(); i++) {
            this.vBox.getChildren().add(createHBox(i));
        }
    }

    private HBox createHBox(int i){
        HBox hBox = new HBox();
        hBox.setId("h-box");
        Insets insets = new Insets(10, 10, 10 ,10);
        hBox.setPadding(insets);
        hBox.setSpacing(50);

        ForecastDate forecastDate = geo.getForecast().getHourly().getData().get(i);
        String time = String.format("%tH.%<tM", forecastDate.getDateTime());
        String temp = String.format("%s" + APIConfig.C, forecastDate.getTemperature());
        String wind = String.format("%.2f" + resourceBundleManager.getString("windSpeedSI"), forecastDate.getWindGust());

        Label tempLabel = new Label(temp);
        Label timeLabel = new Label(time);
        Label windLabel = new Label(wind);
        tempLabel.setId("label");
        timeLabel.setId("label");
        windLabel.setId("wind-label");
        windLabel.setPrefWidth(100);

        hBox.getChildren().add(getImageViewByIcon(forecastDate.getIcon()));
        hBox.getChildren().add(timeLabel);
        HBox hBoxWind = new HBox();
        hBoxWind.getChildren().add(getImageViewByIcon("wind"));
        hBoxWind.getChildren().add(windLabel);
        hBox.getChildren().add(hBoxWind);
        hBox.getChildren().add(tempLabel);
        return new HBox(hBox);
    }

    private ImageView getImageViewByIcon(String icon){
        ImageView imageView = new ImageView(APIConfig.getImage(icon));
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        return imageView;
    }

    @Override
    public void update(Observable o, Object arg) {
        GEO geo = (GEO) arg;
        this.setGeo(geo);
        this.vBox.getChildren().clear();
        this.initialize();
    }

    public void setGeo(GEO geo) {
        this.geo = geo;
    }
}
