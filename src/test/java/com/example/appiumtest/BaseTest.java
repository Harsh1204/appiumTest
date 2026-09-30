package com.example.appiumtest;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testng.annotations.*;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

import static com.example.appiumtest.DriverManager.getDriver;
@Component
@Slf4j
public class BaseTest {
    private static String PLATFORM_NAME;
    public static AppiumDriverLocalService service;
    static URL dynamicServerUrl;
    protected static Properties prop;
    static String platformType;


    public synchronized static Properties initializeProperties() {
        prop = ConfigReader.initializeProperties();

        // Example: Reading data to set up global variables
        platformType = prop.getProperty("appium.platform.namee"); //

        System.out.println("Tests will run on browser: " + platformType);


        return prop;
    }

    public synchronized static AppiumDriverLocalService startAppiumServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingAnyFreePort() // Dynamically finds an open port to avoid conflicts
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
        service = AppiumDriverLocalService.buildService(builder);
        System.out.println("--- Instantiating Appium Local Service ---");
        service.start();
        System.out.println(service.getBasePath());
        return service;
    }
 //   @BeforeClass
    public synchronized static void appiumServer() {
        service=startAppiumServer();
        dynamicServerUrl = service.getUrl();
        System.out.println(dynamicServerUrl);

        //service =  new AppiumServiceBuilder().withIPAddress("http://127.0.0.1").usingPort(4723).build();
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("AshishPixel5"); //emulator
//        options.setApp(System.getProperty("user.dir") + "/src/test/resources/ApiDemos-debug.apk");
//        //URL url = URI.create(String.valueOf(dynamicServerUrl)).toURL();
//        driver = new AndroidDriver(dynamicServerUrl, options);

    }



    @Value("${appium.platform.name}") private String platform_Name;
    @Value("${appium.device.name}") private String device_Name;
    @Value("${appium.udid.id}") private String ud_id;
    @Value("${appium.device.version}") private String device_version;
    @Value("${appium.platform.run}") private String platform_run;

    public static String getPlatformName() {
        return PLATFORM_NAME;
    }

    public static void setPlatformName(String platformName) {
        PLATFORM_NAME = platformName;
    }

    @BeforeMethod
    @Parameters({"platformRun","platformName","deviceName", "udid", "deviceVersion"})
    public synchronized void setUp(@Optional()String platformRun, @Optional()String platformName,
                      @Optional()String deviceName,
                      @Optional()String udid,
                      @Optional()String deviceVersion) throws FileNotFoundException {
        appiumServer();
        initializeProperties();
        System.out.println("Tests will run on browser: " + platformType);
        setPlatformName(platformName);
        if(Objects.isNull(getDriver())) {
            if (platformName.equals("Android") || platformType.equals("Android")) {
                BaseTestAndroid.androidSetup(platformRun, platformName, deviceName, udid, deviceVersion);
            }

            if (platformName.equals("IOS") || platformType.equals("IOS")) {
                BaseTestIos.iosSetup(platformRun, platformName, deviceName, udid, deviceVersion);
            }
        }
    }

    @AfterMethod
    public synchronized void tearDown() throws IOException {
        DriverManager.unloadDriver();
        //after class
        stopAppiumServer();
    }

//@AfterClass
public synchronized static void stopAppiumServer() throws IOException {

    log.info(String.valueOf(service.getUrl()));
    service.stop();
    Runtime.getRuntime().exec("pkill -f appium");

}



}
