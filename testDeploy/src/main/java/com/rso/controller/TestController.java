package com.rso.controller;

import com.rso.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin(origins = "*")
public class TestController {

    @Value("${consumer.api.url}")
    private String consumerApiUrl;

    @GetMapping(path="/consumer")
    public @ResponseBody
    PaymentDto getPayment(@RequestParam int id) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("consumerApiUrl is = " + consumerApiUrl);
        return restTemplate.getForEntity(consumerApiUrl + "consumer/payment/" + id,
                PaymentDto.class)
                .getBody();
    }

    @GetMapping(path="/test")
    public @ResponseBody
    String test() {
        return "Test consumer get endpoint";
    }

}
