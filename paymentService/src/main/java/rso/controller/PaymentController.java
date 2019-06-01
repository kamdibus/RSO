package rso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.PaymentAddDto;
import rso.dto.PaymentDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.StatusType;
import rso.service.PaymentService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="api/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path="{id}")
    public @ResponseBody
    PaymentDto getPayment (@PathVariable final long id) {
       try {
           return paymentService.getPaymentForId(id);
       } catch (InvalidPaymentIdException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payment for this id", e);
        }
    }

    @PostMapping(path = "")
    public @ResponseBody
    PaymentDto addPayment(@RequestBody PaymentAddDto payment) throws ParseException {
        return paymentService.addPayment(payment);
    }

    @GetMapping(path="")
    public @ResponseBody
    List<PaymentDto> getPayments (@RequestParam(required = false) StatusType status, @RequestParam(required = false) Long userId) {
        List<PaymentDto> payments = new ArrayList<PaymentDto>();
        if (status != null && userId != null){
            payments = paymentService.getPaymentsForUserWithStatus(userId, status);

        }
        else if (status != null){
            payments = paymentService.getPaymentsByStatus(status);
        }
        else if (userId != null){
            payments = paymentService.getPaymentsForUser(userId);
        }
        else{
            return paymentService.getPayments();
        }
        if (payments.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return payments;
    }
}
