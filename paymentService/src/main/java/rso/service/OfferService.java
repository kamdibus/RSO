package rso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rso.exceptions.InvalidPaymentIdException;
import rso.model.Offer;
import rso.model.Payment;
import rso.repository.OfferRepository;
import rso.repository.PaymentRepository;

@Component
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer getOfferForId(long id) throws InvalidPaymentIdException {
        if (!offerRepository.findById(id).isPresent()) {
            throw new InvalidPaymentIdException();
        }
        return offerRepository.findById(id).get();
    }

    public Iterable<Offer> getOffers() {
        return offerRepository.findAll();
    }
}
