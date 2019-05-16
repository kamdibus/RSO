package rso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Payment;
import rso.service.PaymentService;

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
    Payment getPaymentInfo (@PathVariable final long id) {
       try {
           return paymentService.getPaymentForId(id);
       } catch (InvalidPaymentIdException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payment for this id", e);
        }
    }

    @GetMapping(path="")
    public @ResponseBody
    Iterable<Payment> getPatments () {
        return paymentService.getPayments();
    }
}
