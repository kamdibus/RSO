package rso.repository;

import org.springframework.data.repository.CrudRepository;
import rso.model.Payment;

public interface PaymentRepository extends CrudRepository <Payment, Long> {
}
