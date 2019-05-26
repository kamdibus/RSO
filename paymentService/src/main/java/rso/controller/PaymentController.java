package rso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.PaymentDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.StatusType;
import rso.service.PaymentService;

import java.util.List;

@Controller
@RequestMapping(path="/payments/")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path="{id}")
    public @ResponseBody
    PaymentDto getPaymentInfo (@PathVariable final long id) {
       try {
           return paymentService.getPaymentForId(id);
       } catch (InvalidPaymentIdException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payment for this id", e);
        }
    }

    @GetMapping(path="")
    public @ResponseBody
    List<PaymentDto> getPatments (@RequestParam(required = false) StatusType status) {
        if (status != null){
            return paymentService.getPaymentsWithStatus(status);
        }
        return paymentService.getPayments();
    }
}
