package com.rso.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.rso.repository")
public class InvoiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}