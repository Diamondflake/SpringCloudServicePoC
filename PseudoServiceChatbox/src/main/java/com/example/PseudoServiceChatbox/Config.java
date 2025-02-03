package com.example.PseudoServiceChatbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationProperties
public class Config {

    private static String value;

    @Value("${AIConfig.value}")
    @ConfigurationProperties
    private void launchValue(String defaultValue) {
        String value = java.net.URLDecoder.decode(defaultValue, StandardCharsets.UTF_8);
        System.out.println("Update on start-up with: " + value);
        setValue(value);
    }

    public static void setValue(String value) {
        Config.value = value;
    }

    public static String getValue() {
        return value;
    }
}