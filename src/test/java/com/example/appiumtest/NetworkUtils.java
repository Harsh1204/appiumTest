package com.example.appiumtest;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
@Component
public class NetworkUtils {
    public static int getAnyFreePort() {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            throw new RuntimeException("Failed to find an available free port", e);
        }
    }
}
