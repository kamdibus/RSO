package com.rso.controller;

import com.rso.exceptions.InvalidPaymentIdException;
import com.rso.model.Payment;
import com.rso.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path="/consumer")
public class ConsumerController {

    private ConsumerService consumerService;

    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping(path="/payment/{id}")
    public @ResponseBody
    Payment getPaymentInfo (@PathVariable final long id) {
       try {
           return consumerService.getPaymentForId(id);
       } catch (InvalidPaymentIdException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payment for this id", e);
        }
    }
}
