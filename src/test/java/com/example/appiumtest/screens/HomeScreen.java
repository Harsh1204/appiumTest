package com.example.appiumtest.screens;

import com.example.appiumtest.BaseTest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.example.appiumtest.DriverManager.getDriver;

public class HomeScreen extends BaseTest {

    IOSDriver driver;

    public HomeScreen(IOSDriver driver){
        this.driver = (IOSDriver) getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
@AndroidFindBy
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"Alerts\"`]")
    private WebElement alerts;

    public AlertsScreen selectAlerts()
    {
        alerts.click();
        return new AlertsScreen(driver);
    }
}
