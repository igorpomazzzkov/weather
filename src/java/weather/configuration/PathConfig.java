package weather.configuration;

import java.io.File;

public class PathConfig {
    public static final String projectPath = System.getProperty("user.dir") + File.separator;
    public static final String mainPath = projectPath + "src" + File.separator + "resources" + File.separator;
    public static String cssPath;
    public static String imagePath;
    public static String fxmlPath;

    static {
        cssPath = mainPath + "css" + File.separator;
        imagePath = mainPath + "img" + File.separator;
        fxmlPath = projectPath +
                "src" + File.separator + "java" + File.separator + "weather" + File.separator + "view" + File.separator;
    }
}
