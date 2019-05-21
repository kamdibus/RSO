package rso.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.OfferAddDto;
import rso.dto.OfferDto;
import rso.exceptions.InvalidOfferIdException;
import rso.model.Offer;
import rso.model.StatusType;
import rso.service.OfferService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    OfferDto getOfferInfo (@PathVariable final long id) {
        try {
            return offerService.getOfferForId(id);
        } catch (InvalidOfferIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No offer for this id", e);
        }
    }

    @GetMapping(path="")
    public @ResponseBody
    List<OfferDto> getOffers (@RequestParam(required = false) StatusType status, @RequestParam(required = false) Long userId) {
        List<OfferDto> offers = new ArrayList<OfferDto>();
        if (status != null && userId != null){
            return offerService.getOffersForUserWithStatus(userId, status);
        }
        if (status != null){
            return offerService.getOffersByStatus(status);
        }
        if (userId != null){
            return offerService.getOffersForUser(userId);
        }
        return offerService.getOffers();
    }

    @PostMapping(path = "")
    public @ResponseBody
    OfferDto addOffer(@RequestBody OfferAddDto offer) throws ParseException {
        return offerService.addOffer(offer);
    }
}

