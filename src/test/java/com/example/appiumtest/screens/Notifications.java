package com.example.appiumtest.screens;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;

import static com.example.appiumtest.DriverManager.getDriver;
@Component
public class Notifications {


    public void checkNotificationText(){
        Dimension size = getDriver().manage().window().getSize();

        // Start at the exact top-center of the screen
        int startX = size.getWidth() / 2;
        int startY = 0;

        // End near the bottom of the screen
        int endX = size.getWidth() / 2;
        int endY = (int) (size.getHeight() * 0.8);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Move to start position, press down, move to end position, release
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        getDriver().perform(Arrays.asList(swipe));
    }
}
