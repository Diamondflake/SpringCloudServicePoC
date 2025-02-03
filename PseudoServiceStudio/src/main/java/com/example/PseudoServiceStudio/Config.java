package com.example.PseudoServiceStudio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationProperties
public class Config {

    private static String gitToken;
    private static String gitURL;
    private static String gitLocalPath;

    @Value("${git.token}")
    @ConfigurationProperties
    private void launchTokenSet(@Value("${git.token}") String token, @Value("${git.url}") String url, @Value("${git.path}") String localPath) {
        System.out.println("Update");
        gitToken = token;
        gitURL = url;
        gitLocalPath = localPath;
    }

    public static String getGitToken() {
        return gitToken;
    }

    public static String getGitURL() {
        return gitURL;
    }

    public static String getGitLocalPath() {
        return gitLocalPath;
    }

}