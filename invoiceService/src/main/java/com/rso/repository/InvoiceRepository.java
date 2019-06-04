package com.rso.repository;

import com.rso.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends MongoRepository<Invoice, Long> {

//    static void saveInvoice(Invoice invoiceToCreate) {
//    }

    Invoice findFirstById(@Param("id") long id);

//    void deleteBySupplierIdOrConsumerId(
//            @Param("supplierId") long supplierId,
//            @Param("consumerId") long consumerId
//    );
//
//    List<Invoice> findBySupplierIdOrConsumerId(
//            @Param("supplierId") long supplierId,
//            @Param("consumerId") long consumerId
//    );
}
