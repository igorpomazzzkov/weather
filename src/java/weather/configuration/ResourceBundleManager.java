package weather.configuration;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private static ResourceBundleManager resourceBundleManager;
    private ResourceBundle resourceBundle;
    private static final String bundleName = "text";

    private ResourceBundleManager(){
        resourceBundle = ResourceBundle.getBundle(bundleName, Locale.getDefault());
    }

    public static ResourceBundleManager getInstance(){
        if(resourceBundleManager == null){
            resourceBundleManager = new ResourceBundleManager();
        }
        return resourceBundleManager;
    }

    public void changeLocale(Locale locale){
        resourceBundle = ResourceBundle.getBundle(bundleName, locale);
    }

    public String getString(String key){
        return resourceBundle.getString(key);
    }
}
