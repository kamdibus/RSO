package com.rso.repository;

import com.rso.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PaymentDatabaseLoader implements CommandLineRunner {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentDatabaseLoader(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.paymentRepository.save(new Payment(10));
        this.paymentRepository.save(new Payment(20));
        this.paymentRepository.save(new Payment(30));
        this.paymentRepository.save(new Payment(40));
    }
}
