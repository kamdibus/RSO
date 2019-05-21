package rso.repository;

import org.springframework.data.repository.CrudRepository;
import rso.model.Payment;
import rso.model.StatusType;

public interface PaymentRepository extends CrudRepository <Payment, Long> {

    Iterable<Payment> findByStatus(StatusType statusType);

}
