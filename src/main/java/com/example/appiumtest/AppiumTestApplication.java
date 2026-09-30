package com.example.appiumtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AppiumTestApplication {

    public static void main(String[] args) {

        SpringApplication.run(AppiumTestApplication.class, args);

    }

}
