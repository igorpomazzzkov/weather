package weather.configuration;

import java.io.File;

public class PathConfig {
    private static final String mainPath = "src" + File.separator + "resources" + File.separator;
    public static String cssPath;
    public static String imagePath;

    static {
        cssPath = mainPath + "css" + File.separator;
        imagePath = mainPath + "img" + File.separator;
    }
}
