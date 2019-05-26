package rso.repository;

import org.springframework.data.repository.CrudRepository;
import rso.model.Offer;
import rso.model.StatusType;

public interface OfferRepository extends CrudRepository<Offer, Long> {

    Iterable<Offer> findByStatus(StatusType statusType);
    Iterable<Offer> findByUserId(Long userId);
    Iterable<Offer> findByUserIdAndStatus(Long userId, StatusType statusType);
    Iterable<Offer> deleteByUserId(Long userId);

}
