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
import rso.dto.PaymentAddDto;
import rso.dto.PaymentDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Payment;
import rso.model.StatusType;
import rso.repository.OfferRepository;
import rso.repository.PaymentRepository;

import java.text.ParseException;
import java.util.*;
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

    private String getUserType(String token){
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
        String userType = (String) userMeta.get("type");
        return userType;
    }

    public PaymentDto getPaymentForId(long id, String token) throws InvalidPaymentIdException {
        Long userId = getUserId(token);
        if (!paymentRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        Payment payment = paymentRepository.findById(id).get();
        if (payment.getOffer().getConsumerId()!= userId || payment.getOffer().getSupplierId()!= userId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return convertToDto(payment);
    }

    public List<PaymentDto> getPayments(String token) {
        Long userId = getUserId(token);
        String userType = getUserType(token);
        List<Payment> payments = new ArrayList();
        if (userType == "supplier"){
            payments.addAll((Collection<? extends Payment>) paymentRepository.findByOffer_SupplierId(userId));
        }
        else {
            payments.addAll((Collection<? extends Payment>) paymentRepository.findByOffer_ConsumerId(userId));
        }
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public PaymentDto addPayment(PaymentAddDto paymentAddDto, String token) throws ParseException {
        Payment payment = convertToEntity(paymentAddDto);
        Payment paymentCreated = paymentRepository.save(payment);
        return convertToDto(paymentCreated);
    }

    public List<PaymentDto> getPaymentsByStatus(StatusType statusType, String token) {
        Long userId = getUserId(token);
        String userType = getUserType(token);
        List<Payment> payments = new ArrayList();
        if (userType == "supplier"){
            payments.addAll((Collection<? extends Payment>) paymentRepository.findByStatusAndOffer_SupplierId(statusType, userId));
        }
        else {
            payments.addAll((Collection<? extends Payment>) paymentRepository.findByStatusAndOffer_ConsumerId(statusType, userId));
        }
        return payments.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

}
