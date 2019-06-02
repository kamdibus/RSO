package rso.controller;

import com.auth0.exception.Auth0Exception;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rso.dto.OfferAddDto;
import rso.dto.OfferDto;
import rso.dto.OfferEditDto;
import rso.exceptions.InvalidOfferIdException;
import rso.model.Offer;
import rso.model.StatusType;
import rso.service.OfferService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@Controller
@RequestMapping(path="api/offers")
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
    OfferDto getOfferInfo (@PathVariable final long id, @RequestHeader("authorization") String token) {
        try {
            return offerService.getOfferForId(id, token);
        } catch (InvalidOfferIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No offer for this id", e);
        }
    }

    @GetMapping(path="")
    public @ResponseBody
    List<OfferDto> getOffers (@RequestParam(required = false) StatusType status, @RequestHeader("authorization") String token) throws Auth0Exception {
        List<OfferDto> offers = new ArrayList<OfferDto>();
        if (status != null) {
            offers = offerService.getOffersByStatus(status, token);
        } else {
        return offerService.getOffers(token);
        }
        if (offers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return offers;
    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody
    ResponseEntity deleteOffer(@PathVariable final long id, @RequestHeader("authorization") String token){
        try {
            offerService.deleteOffer(id, token);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (InvalidOfferIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No offer for this id", e);
        }
    }

    @DeleteMapping(path = "")
    public @ResponseBody
    ResponseEntity deleteOffersForUser(@RequestHeader("authorization") String token){
        offerService.deleteOffersForUser(token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "")
    public @ResponseBody
    OfferDto addOffer(@RequestBody OfferAddDto offer, @RequestHeader("authorization") String token) throws ParseException {

        return offerService.addOffer(offer, token);
    }

    @PatchMapping(path = "{id}")
    public @ResponseBody
    OfferDto editOffer(@PathVariable final Long id, @RequestBody OfferEditDto offer,  @RequestHeader("authorization") String token){
        return offerService.editOffer(id, offer, token);
    }
}

