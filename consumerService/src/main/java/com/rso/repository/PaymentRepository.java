package com.rso.repository;

import com.rso.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository <Payment, Long> {
}
