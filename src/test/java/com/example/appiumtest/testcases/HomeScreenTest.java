package com.example.appiumtest.testcases;

import com.example.appiumtest.BaseTest;
import com.example.appiumtest.screens.AlertsScreen;
import com.example.appiumtest.screens.HomeScreen;
import io.appium.java_client.ios.IOSDriver;
import org.testng.annotations.Test;

import static com.example.appiumtest.DriverManager.getDriver;

public class HomeScreenTest extends BaseTest {

    @Test
    public void alertPresentClick() throws InterruptedException {
        HomeScreen h=new HomeScreen((IOSDriver) getDriver());

        AlertsScreen a=h.selectAlerts();
        a.isLabelDisplayed();
        //verify
        Thread.sleep(1000);
        ((IOSDriver) getDriver()).terminateApp("com.ashishguptapersonalteam.IntegrationApp");
            Thread.sleep(1000);
        ((IOSDriver) getDriver()).activateApp("com.ashishguptapersonalteam.IntegrationApp");
        Thread.sleep(1000);
        ((IOSDriver) getDriver()).terminateApp("com.ashishguptapersonalteam.IntegrationApp");
        Thread.sleep(1000);


    }
}
