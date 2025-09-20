package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CapabilitiesReader {
    private static Properties properties;

    public static void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/capabilities.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getAllProperties() {
        if (properties == null) loadProperties();
        return properties;
    }
}
