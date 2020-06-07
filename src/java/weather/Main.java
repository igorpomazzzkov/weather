package weather;

import com.jwebmp.plugins.skycons.SkyIcon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import weather.configuration.PathConfig;
import weather.configuration.ResourceBundleManager;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Locale;

public class Main extends Application {
    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
        ResourceBundleManager resourceBundleManager = ResourceBundleManager.getInstance();
        resourceBundleManager.changeLocale(new Locale("ru", "RU"));
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL cssFilePath = Paths.get(PathConfig.cssPath + "main.css").toUri().toURL();
        URL fxmlFilePath = Paths.get(PathConfig.fxmlPath + "main.fxml").toUri().toURL();

        Parent loader = FXMLLoader.load(fxmlFilePath);
        Scene scene = new Scene(loader);
        scene.getStylesheets().add(cssFilePath.toString());
        stage.setScene(scene);
        File imageFile = new File(PathConfig.imagePath + "sun.png");
        Image image = new Image(imageFile.toURI().toString());
        stage.getIcons().add(image);
        stage.setTitle("Weather forecast");
        stage.show();
    }
}
