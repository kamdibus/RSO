package com.rso.dto;
import lombok.Data;
import java.text.SimpleDateFormat;


@Data
public class InvoiceEntityDto {

    private Long id;
    private SimpleDateFormat date;
    private Long supplierId;
    private Long consumerId;
    private Long amount;
    private String otherData;

    public InvoiceEntityDto() {}
}
