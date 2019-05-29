package com.rso.controller;

import com.rso.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/test")
public class TestController {

    @Value("${consumer.api.url}")
    private String consumerApiUrl;

    @GetMapping(path="/consumer")
    public @ResponseBody
    PaymentDto getPayment(@RequestParam int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(consumerApiUrl + "/payments/",
                PaymentDto.class)
                .getBody();
    }

    @GetMapping(path="/testDeploy")
    public @ResponseBody
    String test() {
        return "Test consumer get endpoint v1";
    }

}
