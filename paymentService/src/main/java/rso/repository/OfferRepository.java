package rso.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import rso.model.Offer;
import rso.model.StatusType;

public interface OfferRepository extends MongoRepository<Offer, Long> {

    Iterable<Offer> findBySupplierId(Long userId);
    Iterable<Offer> findByConsumerId(Long userId);
    Iterable<Offer> findBySupplierIdAndStatus(Long userId, StatusType statusType);
    Iterable<Offer> findByConsumerIdAndStatus(Long userId, StatusType statusType);
    Iterable<Offer> deleteBySupplierId(Long userId);
    Iterable<Offer> deleteByConsumerId(Long userId);

}
