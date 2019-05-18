package rso.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.OfferDto;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Offer;
import rso.service.OfferService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/offers/")
public class OfferController {

    private OfferService offerService;

    private ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    private OfferDto convertToDto(Offer offer) {
        OfferDto offerDto = modelMapper.map(offer, OfferDto.class);
        offerDto.setCreationDate(offer.getCreationDate());
        offerDto.setStatus(offer.getStatus());
        offerDto.getDiscount(offer.getDiscount());
        return offerDto;
    }

    @GetMapping(path="{id}")
    public @ResponseBody
    Offer getOfferInfo (@PathVariable final long id) {
        try {
            return offerService.getOfferForId(id);
        } catch (InvalidPaymentIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No payment for this id", e);
        }
    }

    @GetMapping(path="")
    public @ResponseBody
    Iterable<Offer> getOffers () {
        return offerService.getOffers();
    }
}

