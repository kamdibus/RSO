package rso.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rso.dto.OfferAddDto;
import rso.dto.OfferDto;
import rso.exceptions.InvalidOfferIdException;
import rso.model.Offer;
import rso.model.StatusType;
import rso.repository.OfferRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
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

    public OfferDto getOfferForId(long id) throws InvalidOfferIdException {
        if (!offerRepository.findById(id).isPresent()) {
            throw new InvalidOfferIdException();
        }
        Offer offer = offerRepository.findById(id).get();
        return convertToDto(offer);
    }

    public List<OfferDto> getOffers() {
        List<Offer> offers = (List<Offer>) offerRepository.findAll();
        return offers.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<OfferDto> getOffersByStatus(StatusType statusType) {
        List<Offer> offers = (List<Offer>) offerRepository.findByStatus(statusType);
        return offers.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<OfferDto> getOffersForUser(Long userId) {
        List<Offer> offers = (List<Offer>) offerRepository.findByUserId(userId);
        return offers.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public List<OfferDto> getOffersForUserWithStatus(Long userId, StatusType statusType) {
        List<Offer> offers = (List<Offer>) offerRepository.findByUserIdAndStatus(userId, statusType);
        return offers.stream()
                .map(post -> convertToDto(post))
                .collect(Collectors.toList());
    }

    public OfferDto addOffer(OfferAddDto offerDto) throws ParseException {
        Offer offer = convertToEntity(offerDto);
        Offer offerCreated = offerRepository.save(offer);
        return convertToDto(offerCreated);
    }

    public void deleteOffer(Long offerId) throws InvalidOfferIdException {
        offerRepository.deleteById(offerId);
    }

    @Transactional
    public void deleteOffersForUser(Long userId) {
        offerRepository.deleteByUserId(userId);
    }
}
