package rso.service;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.APIException;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.auth0.net.Request;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.OfferAddDto;
import rso.dto.OfferDto;
import rso.dto.OfferEditDto;
import rso.exceptions.InvalidOfferIdException;
import rso.model.Offer;
import rso.model.StatusType;
import rso.repository.OfferRepository;
import rso.util.MongoSequenceGeneratorService;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class OfferService {

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
    public OfferService(OfferRepository offerRepository,
                        MongoSequenceGeneratorService mongoSequenceGeneratorService) {
        this.offerRepository = offerRepository;
        this.mongoSequenceGeneratorService = mongoSequenceGeneratorService;
    }

    private OfferDto convertToDto(Offer offer) {
        ModelMapper modelMapper = new ModelMapper();
        OfferDto offerDto = modelMapper.map(offer, OfferDto.class);
        return offerDto;
    }

    private Offer convertToEntity(OfferAddDto offerAddDto) throws ParseException {
        ModelMapper modelMapper = new ModelMapper();
        Offer offer = modelMapper.map(offerAddDto, Offer.class);
        offer.setCreationDate(new Date());
        offer.setExpirationDate(new Date());
        offer.setStatus(StatusType.pending);
        return offer;
    }

    private userData getUserData(String token){
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
        String userType = (String) userMeta.get("type");
        userData data = new userData(userId, userType);
        return data;
    }

    public OfferDto getOfferForId(long id, String token) throws InvalidOfferIdException {
        Long userId = getUserData(token).getId();
        if (!offerRepository.findById(id).isPresent()) {
            throw new InvalidOfferIdException();
        }
        Offer offer = offerRepository.findById(id).get();
        if (userId != offer.getSupplierId() && userId != offer.getConsumerId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return convertToDto(offer);
    }

    public List<OfferDto> getOffers(String token) {
        Long userId = getUserData(token).getId();
        String userType = getUserData(token).getType();
        List<Offer> offers = new ArrayList();
        if (userType == "supplier"){
            offers.addAll((Collection<? extends Offer>) offerRepository.findBySupplierId(userId));
        }
        else {
            offers.addAll((Collection<? extends Offer>) offerRepository.findByConsumerId(userId));
        }
        return offers.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<OfferDto> getOffersByStatus(StatusType statusType, String token) {
        Long userId = getUserData(token).getId();
        String userType = getUserData(token).getType();
        List<Offer> offers = new ArrayList();
        if (userType == "supplier"){
            offers.addAll((Collection<? extends Offer>) offerRepository.findBySupplierIdAndStatus(userId, statusType));
        }
        else {
            offers.addAll((Collection<? extends Offer>) offerRepository.findByConsumerIdAndStatus(userId, statusType));
        }
        return offers.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public OfferDto addOffer(OfferAddDto offerDto, String token) throws ParseException {
        Offer offer = convertToEntity(offerDto);
        Long userId = getUserData(token).getId();
        String userType = getUserData(token).getType();
        if (userType == "supplier"){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        offer.setConsumerId(userId);
//        Offer offerCreated = offerRepository.save(offer);
        Offer offerCreated = saveOfferInMongoDb(offer);
        return convertToDto(offerCreated);
    }

    private Offer saveOfferInMongoDb(Offer newOffer) {
        newOffer.setId(mongoSequenceGeneratorService.generateSequence(Offer.SEQUENCE_NAME));
        offerRepository.save(newOffer);
        return newOffer;
    }

    public void deleteOffer(Long offerId, String token) throws InvalidOfferIdException {
        offerRepository.deleteById(offerId);
    }

    @Transactional
    public void deleteOffersForUser(String token) {
        Long userId = getUserData(token).getId();
        offerRepository.deleteById(userId);
    }

    public OfferDto editOffer(Long id, OfferEditDto editOfferDto, String token) {
        Long userId = getUserData(token).getId();
        Offer offer = offerRepository.findById(id).get();
        if (offer.getSupplierId() != userId){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        offer.setStatus(editOfferDto.getStatus());
        offerRepository.save(offer);
        return convertToDto(offer);
    }

    @Getter
    @Setter
    class userData{
        Long id;
        String type;
        public userData(Long id, String type){
            this.id = id;
            this.type = type;
        }
    }
}
