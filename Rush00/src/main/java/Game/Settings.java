package Game;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    static private final String BASE_PATH = "./src/main/resources/application-";
    static private final String PRODUCTION_PATH = "./src/main/resources/application-production.properties";
    static private final String DEV_PATH = "./src/main/resources/application-dev.properties";
    static private final String PROPERTIES = ".properties";
    static private final String FILE_NOT_FOUND = "File not found";
    private static String _path;

    public static Properties getSettings(String profile) {
        if (profile.equals("production")){
            _path = PRODUCTION_PATH;
        } else if (profile.equals("dev")){
            _path = DEV_PATH;
        } else {
            _path = BASE_PATH + profile + PROPERTIES;
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(_path));
        } catch (IOException e) {
            System.err.println(FILE_NOT_FOUND);
            System.exit(-1);
        }
        return properties;
    }
}
