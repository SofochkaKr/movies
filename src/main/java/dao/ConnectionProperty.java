package dao;

import java.io.IOException;
import java.util.Properties;

public class ConnectionProperty {

    private static final Properties PROPERTY_CONFIG = new Properties();

    public ConnectionProperty() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            PROPERTY_CONFIG.load(classLoader.getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Cannot load config.properties", e);
        }
    }

    public String getProperty(String property) {
        return PROPERTY_CONFIG.getProperty(property);
    }
}
