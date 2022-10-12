package com.finalchallenge.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EntityScan(basePackages = { "com.finalchallenge.app.entities" })
@EnableJpaRepositories(basePackages = { "com.finalchallenge.app.repositories" })
public class AyiFinalSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyiFinalSpringApplication.class, args);
	}

}
