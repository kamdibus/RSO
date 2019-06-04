package com.rso.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Document(collection = "invoices")
@Data
public class Invoice {

    @Transient
    public static final String SEQUENCE_NAME = "invoices_sequence";

    @Id
    private long id;

    private Date date;

    private long supplierId;

    private long consumerId;

    private long amount;

    private String otherData;

}
