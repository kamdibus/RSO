package com.rso.repository;

import com.rso.model.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository <Invoice, Long> {
}

