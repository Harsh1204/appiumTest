package com.example.appiumtest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static com.example.appiumtest.DriverManager.getDriver;

public class iosTests extends BaseTest{
    @Test(groups = {"smoke"})
    public synchronized void  test() throws InterruptedException {
        if(System.getProperty("platformName").equals("Android")){
            Thread.sleep(1000);
            getDriver().findElement(AppiumBy.accessibilityId("Preference")).click();
            Thread.sleep(1000);
        }else{
            getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Scrolling\"`]")).click();
        }

    }
    @Test(groups = {"smoke"})
    public void test1() throws MalformedURLException, InterruptedException {
        if (System.getProperty("platformName").equals("Android")) {
            Thread.sleep(1000);
            getDriver().findElement(AppiumBy.accessibilityId("Preference")).click();
            Thread.sleep(1000);
        } else {
            Thread.sleep(1000);
            getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Scrolling\"`]")).click();
            Thread.sleep(1000);
        }


    }

    @Test(groups = {"reg"})
    public void testt() throws MalformedURLException, InterruptedException {

        if (System.getProperty("platformName").equals("Android")) {
            Thread.sleep(1000);
            getDriver().findElement(AppiumBy.accessibilityId("Preference")).click();
            Thread.sleep(1000);
        } else {
            Thread.sleep(1000);
            getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Scrolling\"`]")).click();
            Thread.sleep(1000);
        }
    }


    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    /**
     * Create (POST Request)
     */

    @Test
    public void testCreate () {
        // JSON request body
        String requestBody = """
                    {
                        "title": "foo",
                        "body": "bar",
                        "userId": 1
                    }
                    """;

        // Sending POST request
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL);

        // Printing and validating the response
        System.out.println("Create Response: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 201, "Status Code Check");
        Assert.assertTrue(response.getBody().asString().contains("foo"), "Response Body Check");
    }
}
