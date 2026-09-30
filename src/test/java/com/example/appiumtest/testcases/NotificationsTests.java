package com.example.appiumtest.testcases;

import com.example.appiumtest.BaseTest;
import com.example.appiumtest.screens.Notifications;
import io.appium.java_client.AppiumBy;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.appiumtest.DriverManager.getDriver;

@Component
public class NotificationsTests extends BaseTest {
    @Test
    public static void simulateNoti() throws InterruptedException {
    getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Scrolling\"`]")).click();

        Map<String, Object> pushArgs = getPushArgs();

//     getDriver().executeScript("mobile: pushNotification", pushArgs);
        getDriver().executeScript("mobile: pushNotification", pushArgs);
        Thread.sleep(2000);
     Map<String, Object> expectArgs = new HashMap<>();
     expectArgs.put("name", "com.ashishguptapersonalteam.IntegrationApp");
     expectArgs.put("timeoutSeconds", 10);
     getDriver().executeScript("mobile: expectNotification", expectArgs);
    }

    private static @NonNull Map<String, Object> getPushArgs() {
        Map<String, Object> alert = new HashMap<>();
        alert.put("title", "Order Update");
        alert.put("body", "Your order #1234 has been shipped");

        Map<String, Object> aps = new HashMap<>();
        aps.put("alert", alert);

        Map<String, Object> payload = new HashMap<>();
        payload.put("aps", aps);
        payload.put("orderId", "1234");
        payload.put("deepLink", "/orders/1234");

        Map<String, Object> pushArgs = new HashMap<>();
        pushArgs.put("bundleId", "com.ashishguptapersonalteam.IntegrationApp");
        pushArgs.put("payload", payload);
        return pushArgs;
    }

    @Test
    public void notify2() throws InterruptedException {
        Map<String, Object> args = new HashMap<>();
        args.put("bundleId", "com.ashishguptapersonalteam.IntegrationApp"); // Replace with your app's Bundle ID
        args.put("payload", Map.of(
                "aps", Map.of(
                        "alert", "Hello, this is a test notification!"
                )
        ));

// Execute the command via Appium
        getDriver().executeScript("mobile: pushNotification", args);
    Thread.sleep(2000);
    }
    @Test
    void checkNotificationTextTest() throws InterruptedException {
        getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Scrolling\"`]")).click();
        //inject notification
        Notifications notifications = new Notifications();
        notify2();
        notifications.checkNotificationText();


        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        // Locate the notification cells (adjust XCUIElementType based on your Appium Inspector output)
        By notificationLocator = By.className("XCUIElementTypeCell");
        wait.until(ExpectedConditions.presenceOfElementLocated(notificationLocator));

        List<WebElement> notifications1 = getDriver().findElements(notificationLocator);

        for (WebElement notification : notifications1) {
            // Print the visible text/label of each notification card
            System.out.println("Notification: " + notification.getAttribute("label"));
        }
    }
}
