package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance = null;
    private Properties properties;

    private ConfigReader() {
        loadProperties();
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        String configFilePath = "src/main/resources/config.properties"; // Default path
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + configFilePath);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
