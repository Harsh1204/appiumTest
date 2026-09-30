package com.example.appiumtest;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
@Component
public class ConfigReader {
    private static Properties properties;

    public static Properties initializeProperties() {
        properties = new Properties(); //
        try {
            // Locate the properties file relative to the project directory
            String path = System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream fileInput = new FileInputStream(path); //
            properties.load(fileInput); //
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties" +
                    " file.");
        }
        return properties;
    }
    public static Properties initializeProperties(String filePath) {
        properties = new Properties(); //
        try {
            // Locate the properties file relative to the project directory
            String path = System.getProperty("user.dir") + filePath;
            FileInputStream fileInput = new FileInputStream(path); //
            properties.load(fileInput); //
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties" +
                    " file.");
        }
        return properties;
    }
}