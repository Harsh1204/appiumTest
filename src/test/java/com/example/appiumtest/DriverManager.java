package com.example.appiumtest;

import io.appium.java_client.AppiumDriver;
import org.springframework.stereotype.Component;

@Component
public class DriverManager extends BaseTest {

  private DriverManager(){}
    // ThreadLocal container guarantees thread confinement at the class level
    private static  ThreadLocal<AppiumDriver> threadLocalDriver = new ThreadLocal<>();


    // Sets the driver instance for the calling thread
    public static void setDriver(AppiumDriver driver) {

        threadLocalDriver.set(driver);
    }

    // Retrieves the unique driver instance for the calling thread
    public static AppiumDriver getDriver() {

        return threadLocalDriver.get();
    }

    // Safely unloads the driver instance from the current thread to prevent memory leaks
    public static void unloadDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}