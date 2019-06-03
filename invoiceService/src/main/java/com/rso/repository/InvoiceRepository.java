package com.rso.repository;

import com.rso.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {

    Invoice findFirstById(@Param("id") long id);
    void deleteBySupplierIdOrConsumerId(
            @Param("supplierId") long supplierId,
            @Param("consumerId") long consumerId
    );
}
