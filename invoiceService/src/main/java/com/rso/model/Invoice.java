package com.rso.model;

import com.rso.dto.InvoiceEntityDto;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Document(collection = "invoices")
@Data
public class Invoice {

    @Transient
    public static final String SEQUENCE_NAME = "invoices_sequence";

    @Id
    private long id;

    private String data;

    public Invoice(long id, final String data) {
        this.id = id;
        this.data = data;
    }

    public Invoice() {}

    public Invoice(InvoiceEntityDto newData, long invoiceId) {
        this.id = invoiceId;
        this.data = newData.getData();
    }
}
