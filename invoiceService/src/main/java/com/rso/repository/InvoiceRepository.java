package com.rso.repository;

import com.rso.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends MongoRepository<Invoice, Long> {

    Invoice findFirstById(@Param("id") long id);
}
