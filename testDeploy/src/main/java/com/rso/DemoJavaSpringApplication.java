package com.rso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.rso.repository")
public class DemoJavaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJavaSpringApplication.class, args);

	}
}
