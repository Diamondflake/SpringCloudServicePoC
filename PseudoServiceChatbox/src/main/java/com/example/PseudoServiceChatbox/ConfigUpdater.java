package com.example.PseudoServiceChatbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Configuration
public class ConfigUpdater {

    private final Environment environment;

    public ConfigUpdater(Environment environment) {
        this.environment = environment;
    }

    @EventListener(EnvironmentChangeEvent.class)
    public void updateValue() throws UnsupportedEncodingException {
        String value = environment.getProperty("AIConfig.value");
        value = java.net.URLDecoder.decode(value, StandardCharsets.UTF_8);

        System.out.println("Update from ConfigUpdater with: " + value);
        Config.setValue(value);
    }
}