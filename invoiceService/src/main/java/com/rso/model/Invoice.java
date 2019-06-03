package com.rso.model;

import com.rso.dto.InvoiceDto;
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

    public Invoice(InvoiceDto newInvoiceData) {
        this.date = newInvoiceData.getDate();
        this.supplierId = newInvoiceData.getSupplierId();
        this.consumerId = newInvoiceData.getConsumerId();
        this.amount = newInvoiceData.getAmount();
        this.otherData = newInvoiceData.getOtherData();
    }
    public Invoice() {}

}
