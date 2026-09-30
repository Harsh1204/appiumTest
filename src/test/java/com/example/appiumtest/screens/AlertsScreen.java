package com.example.appiumtest.screens;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.example.appiumtest.DriverManager.getDriver;

public class AlertsScreen {
    IOSDriver driver;
    public AlertsScreen(IOSDriver driver) {
        this.driver= (IOSDriver) getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @iOSXCUITFindBy(accessibility = "label")
    private WebElement label;

    public void isLabelDisplayed()
    {


        System.out.println(label.isDisplayed());

    }
}
