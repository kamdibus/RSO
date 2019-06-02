package rso.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.OfferDto;
import rso.dto.PaymentAddDto;
import rso.dto.PaymentDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Offer;
import rso.model.Payment;
import rso.model.StatusType;
import rso.repository.OfferRepository;
import rso.repository.PaymentRepository;
import rso.util.MongoSequenceGeneratorService;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PaymentService {
    private PaymentRepository paymentRepository;
    private OfferRepository offerRepository;

    @Value("${auth0.domain}")
    private String domain;

    @Value("${auth0.clientId}")
    private String clientId;

    @Value("${auth0.clientSecret}")
    private String clientSecret;

    @Value("${auth0.userMetadataUrl}")
    private String metaUrl;

    private MongoSequenceGeneratorService mongoSequenceGeneratorService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          MongoSequenceGeneratorService mongoSequenceGeneratorService) {
        this.paymentRepository = paymentRepository;
        this.mongoSequenceGeneratorService = mongoSequenceGeneratorService;
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

    private Long getUserId(String token){
        AuthAPI auth = new AuthAPI(domain, clientId, clientSecret);
        Request<UserInfo> request2 = auth.userInfo(token.replace("Bearer ", ""));
        UserInfo info = null;
        try {
            info = request2.execute();
        } catch (APIException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (Auth0Exception exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        HashMap userMeta = (HashMap) info.getValues().get(metaUrl);
        Long userId = Long.parseLong((String) userMeta.get("nip"));
        return userId;
    }

    public PaymentDto getPaymentForId(long id, String token) throws InvalidPaymentIdException {
        Long userId = getUserId(token);
        if (!paymentRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        Payment payment = paymentRepository.findById(id).get();
        if (payment.getOffer().getUserId()!= userId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return convertToDto(payment);
    }

    public List<PaymentDto> getPayments(String token) {
        Long userId = getUserId(token);
        List<Payment> payments = (List<Payment>) paymentRepository.findByOffer_UserId(userId);
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public PaymentDto addPayment(PaymentAddDto paymentAddDto, String token) throws ParseException {
        Payment payment = convertToEntity(paymentAddDto);
//        Payment paymentCreated = paymentRepository.save(payment);
        Payment paymentCreated = savePaymentInMongoDb(payment);
        return convertToDto(paymentCreated);
    }

    private Payment savePaymentInMongoDb(Payment newPayment) {
        newPayment.setId(mongoSequenceGeneratorService.generateSequence(Payment.SEQUENCE_NAME));
        paymentRepository.save(newPayment);
        return newPayment;
    }

    public List<PaymentDto> getPaymentsByStatus(StatusType statusType, String token) {
        Long userId = getUserId(token);
        List<Payment> payments = (List<Payment>) paymentRepository.findByStatusAndOffer_UserId(statusType, userId);
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

}
