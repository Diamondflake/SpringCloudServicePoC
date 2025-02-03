package com.example.ServiceSpringCloudConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServiceSpringCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSpringCloudConfigApplication.class, args);
	}

}
