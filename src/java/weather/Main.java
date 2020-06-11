package weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import weather.configuration.PathConfig;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL cssFilePath = Paths.get(PathConfig.cssPath + "main.css").toUri().toURL();
        URL fxmlFilePath = Paths.get(PathConfig.fxmlPath + "main.fxml").toUri().toURL();
        String hourly = Paths.get(PathConfig.cssPath + "hourly.css").toUri().toURL().toString();

        Parent loader = FXMLLoader.load(fxmlFilePath);
        Scene scene = new Scene(loader);
        scene.getStylesheets().add(cssFilePath.toString());
        scene.getStylesheets().add(hourly);
        stage.setScene(scene);
        File imageFile = new File(PathConfig.imagePath + "sun.png");
        Image image = new Image(imageFile.toURI().toString());
        stage.getIcons().add(image);
        stage.setTitle("Weather forecast");
        stage.show();
    }
}
