package com.example.PseudoServiceStudio;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootApplication
public class PseudoServiceStudioApplication {

	public static void main(String[] args) throws GitAPIException, IOException {
		SpringApplication.run(PseudoServiceStudioApplication.class, args);
	}

}
