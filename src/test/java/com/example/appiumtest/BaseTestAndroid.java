package com.example.appiumtest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Objects;
import java.util.Properties;

import static com.example.appiumtest.BaseTest.dynamicServerUrl;
import static com.example.appiumtest.DriverManager.getDriver;

public class BaseTestAndroid {
    protected static Properties prop;
    static String platformType;
    static String device_Name;
    static String ud_id;
    static AndroidDriver localDriver;




    public static Properties initializeAndroidProperties() {
        String filePath="/src/test/resources/android.config.properties";
        prop = ConfigReader.initializeProperties(filePath);

        // Example: Reading data to set up global variables
        platformType = prop.getProperty("appium.platform.name"); //
        device_Name = prop.getProperty("appium.device.name");
        ud_id = prop.getProperty("appium.udid.id");

        System.out.println("Tests will run on browser: " + platformType);
        System.out.println("Tests will run on browser: " + device_Name);
        System.out.println("Tests will run on browser: " + ud_id);

        return prop;
    }
    public static String path =System.getProperty("user.dir");
    //@Value("${appium.platform.name}") private String platform_Name;
    //@Value("${appium.platform.namee}") private String device_Name;
    //@Value("${appium.udid.ide}") private String ud_id;
//    @Value("${appium.device.version}") private String device_version;
//    @Value("${appium.platform.run}") private String platform_run;
    @Parameters({"platformRun","platformName","deviceName", "udid", "deviceVersion"})
    public static AppiumDriver  androidSetup(@Optional()String platformRun, @Optional()String platformName,
                                             @Optional()String deviceName,
                                             @Optional()String udid,
                                             @Optional()String deviceVersion){
        if(Objects.isNull(getDriver())){
            try {
                initializeAndroidProperties();
                System.out.println("Tests will run on browser: " + platformType);
                System.out.println("Tests will run on browser: " + device_Name);
                System.out.println("Tests will run on browser: " + ud_id);
                System.out.println("$$$$$$$$" + System.getProperty("platform.name"));
                UiAutomator2Options options = new UiAutomator2Options();
                if (!System.getProperty("platformName").isBlank()) {

                    System.out.println("--------" + System.getProperty("deviceName"));
                    System.out.println("--------" + System.getProperty("udid"));
                    options.setDeviceName(System.getProperty("deviceName"));
                    options.setUdid(System.getProperty("udid"));
                } else {
                    System.out.println("$$$$$$$$" + device_Name);
                    System.out.println("$$$$$$$$" + ud_id);
                    options.setDeviceName(device_Name);
                    options.setUdid(ud_id);
                }

                options.setApp(path + "/src/main/resources/ApiDemos-debug.apk");
                //URL url = URI.create(String.valueOf(dynamicServerUrl)).toURL();
//            driver = new AndroidDriver(dynamicServerUrl, options);
//            UiAutomator2Options options = new UiAutomator2Options()
//                    .setUdid(udid)
//                    .setPlatformVersion(platformVersion)
//                    .setAutomationName("UiAutomator2")
//                    .setAppPackage("com.example.app")
//                    .setAppActivity(".MainActivity");
//
//            // Point to the unique Appium server port dedicated to this thread/device
//            URL url = new URL("http://127.0.0.1:" + appiumPort + "/");

                // Instantiate driver and assign it to the ThreadLocal scope
                //AndroidDriver localDriver = new AndroidDriver(dynamicServerUrl, options);

                DriverManager.setDriver(new AndroidDriver(dynamicServerUrl, options));
            } catch(Exception e){
                e.printStackTrace();
            }
        }


        return localDriver;
    }
}
