package com.rso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class DemoJavaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoJavaSpringApplication.class, args);
	}
}
