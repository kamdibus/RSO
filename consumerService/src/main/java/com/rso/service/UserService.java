package com.rso.service;

import com.rso.dto.UserEntityDto;
import com.rso.model.User;
import com.rso.model.UserStatus;
import com.rso.repository.UserRepository;
import com.rso.util.DtoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class UserService {

    private UserRepository userRepository;

    private DtoHandler dtoHandler;

    @Value("${payment.api.url}")
    private String apiPaymentService;

    @Value("${offer.api.url}")
    private String apiOfferService;


    @Autowired
    public UserService (UserRepository userRepository, DtoHandler dtoHandler) {
        this.userRepository = userRepository;
        this.dtoHandler = dtoHandler;
    }


    public ResponseEntity<?> getCompanyDetailsForNip(String nipNumber) {
        User userForId = userRepository.findFirstByNipNumber(nipNumber);
        return mapUserToDto(userForId, UserEntityDto.class);
    }

    private <T> ResponseEntity<?> mapUserToDto(User userForId, Class<T> dtoType) {
        if (userForId != null) {
            T mappedDto = dtoHandler.mapEntityToDto(userForId, dtoType);
            return new ResponseEntity<>(mappedDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> editDetailsForUserId(UserEntityDto newData, Long userId) {
        if (!userRepository.findById(userId).get().getUserStatus().equals(UserStatus.DELETED)) {
            User updatedUser = new User(newData, userId);
            userRepository.save(updatedUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> deleteAccountForUserId(Long userId) {
        if (!getOffersForUserId(userId, "pending").getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            String removalUrl = preparePaymentServiceCallUrl(userId);
            callForOfferRemoval(removalUrl);
        }
        userRepository.findById(userId).map(user -> {
            user.setUserStatus(UserStatus.DELETED);
            return userRepository.save(user);
        });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<?> getOffersForUserId(long userId, String status) {
        String url = prepareUrlForPaymentServiceGet(userId, status, this.apiOfferService);
        return getForPaymentOfferEntities(url);
    }

    private ResponseEntity<?> getForPaymentOfferEntities(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<?> removeOffersForUserId(long userId) {
        String requestUrl = prepareForFullUserOffersDelete(userId);
        return callForOfferRemoval(requestUrl);
    }

    private ResponseEntity<?> callForOfferRemoval(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
    }

    private String preparePaymentServiceCallUrl(long userId) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(this.apiOfferService).path("/".concat(String.valueOf(userId)))
                .build();
        return uri.toUriString();
    }

    private String prepareUrlForPaymentServiceGet(long userId, String status, String host) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(host)
                .queryParam("status", status)
                .queryParam("user_id", userId)
                .build();
        return uri.toUriString();
    }

    private String prepareForFullUserOffersDelete(long userId) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host(this.apiOfferService)
                .queryParam("user_id", userId)
                .build();
        return uri.toUriString();
    }

    public ResponseEntity<?> getPaymentsForUserId(Long userId, String status) {
        String url = prepareUrlForPaymentServiceGet(userId, status, this.apiPaymentService);
        return getForPaymentOfferEntities(url);
    }
}
