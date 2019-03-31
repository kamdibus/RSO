package com.rso.service;

import com.rso.exceptions.InvalidPaymentIdException;
import com.rso.model.Payment;
import com.rso.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConsumerService {

    private PaymentRepository paymentRepository;

    @Autowired
    public ConsumerService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment getPaymentForId(long id) throws InvalidPaymentIdException {
        if (!paymentRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        return paymentRepository.findById(id).get();
    }
}
