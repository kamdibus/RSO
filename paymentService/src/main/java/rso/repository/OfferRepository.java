package rso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import rso.model.Offer;
import rso.model.StatusType;

public interface OfferRepository extends MongoRepository<Offer, Long> {

    Iterable<Offer> findByStatus(StatusType statusType);
    Iterable<Offer> findByUserId(Long userId);
    Iterable<Offer> findByUserIdAndStatus(Long userId, StatusType statusType);
    Iterable<Offer> deleteByUserId(Long userId);

}
