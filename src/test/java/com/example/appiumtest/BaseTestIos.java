package com.example.appiumtest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static com.example.appiumtest.BaseTest.dynamicServerUrl;
import static com.example.appiumtest.DriverManager.getDriver;
@Component
public class BaseTestIos extends BaseTest{
    static Properties prop;
    static String platformType;
    static String device_Name;
    static String ud_id;
    public synchronized static Properties initializeIosProperties() {
        String filePath="/src/test/resources/ios.config.properties";
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
    public static String appPath=System.getProperty("appPath");
    public static String path =System.getProperty("user.dir");
    //@Value("${appium.device.name}") private static String device_Name;
    //@Value("${appium.udid.id}") private static String ud_id;
    @Parameters({"platformRun","platformName","deviceName", "udid", "deviceVersion"})
    public synchronized static AppiumDriver iosSetup(@Optional()String platformRun, @Optional()String platformName,
                          @Optional()String deviceName,
                          @Optional()String udid,
                          @Optional()String deviceVersion) {

        if (Objects.isNull(getDriver())) {
            try {
                initializeIosProperties();
                XCUITestOptions options = new XCUITestOptions();
                if (!platformName.isBlank()) {

                    options.setDeviceName(deviceName);
                    options.setUdid(udid);
                } else {
                    options.setDeviceName(device_Name);
                    options.setUdid(ud_id);
                }

                System.out.println(path);
                System.out.println("AppPath"+path+"/"+appPath);
                options.noReset();
                options.setApp(path+"/"+appPath);
                Map<String, Object> tbOptions = new HashMap<>();
                tbOptions.put("key", "api_key");
                tbOptions.put("secret", "api_secret");
                tbOptions.put("name", "iOS Push Notification Test");
                options.setCapability("tb:options", tbOptions);
                options.setCapability("appium:autoAcceptAlerts", true);

                options.setPlatformVersion("27.0");
                java.util.Optional<Integer> wdaLocalPort = options.getWdaLocalPort();
                wdaLocalPort.ifPresent(System.out::println);

                //options.setUsePreinstalledWda(true);
                int dynamicWdaPort = NetworkUtils.getAnyFreePort();
                System.out.println("Assigning dynamic WDA port: " + dynamicWdaPort);

                options.setWdaLocalPort(dynamicWdaPort);
                //Appium- Webdriver Agent -> IOS Apps.
                options.setWdaLaunchTimeout(Duration.ofSeconds(40));
                //for parallel exection
                //options.setDerivedDataPath("");
                //options.setMjpegServerPort();
                //IOSDriver localDriver = new IOSDriver(dynamicServerUrl, options);
                DriverManager.setDriver(new IOSDriver(dynamicServerUrl, options));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return DriverManager.getDriver();
        }

}
