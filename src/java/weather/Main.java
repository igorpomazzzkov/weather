package weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import weather.configuration.PathConfig;

import java.io.File;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getResource("view" + File.separator + "main.fxml"));
        Scene scene = new Scene(loader);
        File cssFile = new File(PathConfig.cssPath + "main.css");
        scene.getStylesheets().add(cssFile.toURI().toString());
        stage.setScene(scene);
        System.out.println(new File(PathConfig.imagePath + "sun.png").isFile());
        File imageFile = new File(PathConfig.imagePath + "sun.png");
        Image image = new Image(imageFile.toURI().toString());
        stage.getIcons().add(image);
        stage.setTitle("Weather forecast");
        stage.show();
    }
}
