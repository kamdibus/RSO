package com.rso.model;

import com.rso.dto.InvoiceEntityDto;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Data
@Entity
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

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
}
