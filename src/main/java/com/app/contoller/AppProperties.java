package com.app.contoller;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class for reading properties defined in application.properties
 * The property value is retrieved by name, key
 */
public class AppProperties {
    private final static Logger log = Logger.getLogger(AppProperties.class);
    private static Properties properties = null;
    private final static String PROPERTY_FILE = "application.properties";


    private AppProperties() {
    }

    /**
     * Function to retrieve the property defined in application.properties by key name as String.
     */
    public static String getPropertyValue(String prop) {
        try {
            properties = getProperties();
            String value = properties.getProperty(prop);
            log.info(String.format("Property %s was loaded successfully.", prop));
            return value;
        } catch (Exception e) {
            log.error(String.format("Can not read property %s from file %s. Exception: %s.", prop, PROPERTY_FILE, e.getMessage()));
            return null;
        }
    }

    /**
     * Singleton instance of property, the input is loaded once when the property is set to null,
     * otherwise the already instantiated input stream is returned.
     * If the application properties file is changed, the application must be restarted to reflect the changes.
     * */
    protected static Properties getProperties() {
        try {
            if (properties == null) {
                properties = new Properties();
                InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE);
                properties.load(stream);
            }

            return properties;
        } catch (Exception e) {
            log.error(String.format("Can not read property file %s. Exception: %s.", PROPERTY_FILE, e.getMessage()));
            return null;
        }
    }
}