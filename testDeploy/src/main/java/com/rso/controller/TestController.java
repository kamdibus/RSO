package com.rso.controller;

import com.rso.dto.PaymentDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(path="/test")
public class TestController {

    public String clusterIP = "http://35.242.206.172";

    @GetMapping(path="/payment")
    public @ResponseBody
    List<PaymentDto> getPayments() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(clusterIP + "/payments/",
                List.class)
                .getBody();
    }

    @GetMapping(path="/consumer")
    public @ResponseBody
    String getUsersTestString() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(clusterIP + "/users/test",
                String.class)
                .getBody();
    }

    @GetMapping(path="/invoices")
    public @ResponseBody
    String getInvoiceTestString() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(clusterIP + "/invoices/test",
                String.class)
                .getBody();
    }

    @GetMapping(path="/testDeploy")
    public @ResponseBody
    String test() {
        return "Test consumer get endpoint v2";
    }

}
