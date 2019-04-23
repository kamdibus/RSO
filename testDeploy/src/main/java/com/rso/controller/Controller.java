package com.rso.controller;

import com.rso.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "*")
public class Controller {

    @Value("${consumer.api.url}")
    private String consumerApiUrl;

    @PostMapping("/consumer")
    public PaymentDto sentimentAnalysis(@RequestBody int id) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("consumerApiUrl is = " + consumerApiUrl);
        return restTemplate.postForEntity(consumerApiUrl + "consumer/payment/",
                id, PaymentDto.class)
                .getBody();
    }

}
