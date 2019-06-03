package com.rso.model;

import com.rso.dto.InvoiceEntityDto;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Document(collection = "invoices")
@Data
public class Invoice {

    @Transient
    public static final String SEQUENCE_NAME = "invoices_sequence";

    @Id
    private long id;

    private String data;

    private SimpleDateFormat date;

    private long supplierId;

    private long consumerId;

    private long amount;

    private String otherData;

    // TODO: get rid of it
    public Invoice(long id, final String data) {
        this.id = id;
    }

    public Invoice(InvoiceEntityDto newInvoiceData) {
        this.date = newInvoiceData.getDate();
        this.supplierId = newInvoiceData.getSupplierId();
        this.consumerId = newInvoiceData.getConsumerId();
        this.amount = newInvoiceData.getAmount();
        this.otherData = newInvoiceData.getOtherData();
    }
    public Invoice() {}

}
