package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            // First try to load from system environment
            String env = System.getenv("TEST_ENV");
            if (env == null) {
                env = "qa"; // default environment
            }

            // Load properties file
            String propertiesFile = "src/main/resources/config/" + env + ".properties";
            properties.load(new FileInputStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        // First check environment variables
        String value = System.getenv(key);
        if (value != null) {
            return value;
        }
        // Then check properties file
        return properties.getProperty(key);
    }

    public String getPasscode() {
        return getProperty("WALLET_PASSCODE");
    }

    public String getEnvironment() {
        return getProperty("TEST_ENV");
    }
} 