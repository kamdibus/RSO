package rso.repository;

import org.springframework.data.repository.CrudRepository;
import rso.model.Payment;
import rso.model.StatusType;

public interface PaymentRepository extends CrudRepository <Payment, Long> {

    Iterable<Payment> findByStatus(StatusType statusType);
    Iterable<Payment> findByStatusAndOffer_UserId(StatusType statusType, Long userId);
    Iterable<Payment> findByOffer_UserId(Long userId);

}
