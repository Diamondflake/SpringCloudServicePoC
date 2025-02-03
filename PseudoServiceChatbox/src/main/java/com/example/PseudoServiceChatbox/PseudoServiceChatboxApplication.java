package com.example.PseudoServiceChatbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import static java.lang.Thread.sleep;

@SpringBootApplication
public class PseudoServiceChatboxApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(PseudoServiceChatboxApplication.class, args);
	}

}
