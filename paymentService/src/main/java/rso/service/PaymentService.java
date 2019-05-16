package rso.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rso.dto.PaymentDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Payment;
import rso.repository.PaymentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentService {
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    private PaymentDto convertToDto(Payment payment) {
        ModelMapper modelMapper = new ModelMapper();
        PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
        return paymentDto;
    }

    public PaymentDto getPaymentForId(long id) throws InvalidPaymentIdException {
        if (!paymentRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        Payment payment = paymentRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        PaymentDto paymentDto = modelMapper.map(payment, PaymentDto.class);
        return paymentDto;
    }

    public List<PaymentDto> getPayments() {
        List<Payment> payments = (List<Payment>) paymentRepository.findAll();
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }
}
