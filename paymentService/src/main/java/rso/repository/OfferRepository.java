package rso.repository;

import org.springframework.data.repository.CrudRepository;
import rso.model.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
}
