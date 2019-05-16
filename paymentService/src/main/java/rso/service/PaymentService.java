package rso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Payment;
import rso.repository.PaymentRepository;

import java.util.List;

@Component
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment getPaymentForId(long id) throws InvalidPaymentIdException {
        if (!paymentRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        return paymentRepository.findById(id).get();
    }

    public Iterable<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
