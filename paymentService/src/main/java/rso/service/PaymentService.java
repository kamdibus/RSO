package rso.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rso.dto.OfferDto;
import rso.dto.PaymentAddDto;
import rso.dto.PaymentDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Offer;
import rso.model.Payment;
import rso.model.StatusType;
import rso.repository.PaymentRepository;

import java.text.ParseException;
import java.util.Date;
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

    private Payment convertToEntity(PaymentAddDto paymentAddDto) throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        Payment payment = modelMapper.map(paymentAddDto, Payment.class);
        payment.setPaymentDate(new Date());
        payment.setStatus(StatusType.pending);
        return payment;
    }

    public PaymentDto getPaymentForId(long id) throws InvalidPaymentIdException {
        if (!paymentRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        Payment payment = paymentRepository.findById(id).get();
        return convertToDto(payment);
    }

    public List<PaymentDto> getPayments() {
        List<Payment> payments = (List<Payment>) paymentRepository.findAll();
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<PaymentDto> getPaymentsWithStatus(StatusType statusType) {
        List<Payment> payments = (List<Payment>) paymentRepository.findByStatus(statusType);
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public PaymentDto addPayment(PaymentAddDto paymentAddDto) throws ParseException {
        Payment payment = convertToEntity(paymentAddDto);
        Payment paymentCreated = paymentRepository.save(payment);
        return convertToDto(paymentCreated);
    }

    public List<PaymentDto> getPaymentsForUserWithStatus(Long userId, StatusType statusType) {
        List<Payment> payments = (List<Payment>) paymentRepository.findByStatusAndOffer_UserId(statusType, userId);
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<PaymentDto> getPaymentsByStatus(StatusType statusType) {
        List<Payment> payments = (List<Payment>) paymentRepository.findByStatus(statusType);
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<PaymentDto> getPaymentsForUser(Long userId) {
        List<Payment> payments = (List<Payment>) paymentRepository.findByOffer_UserId(userId);
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }
}
