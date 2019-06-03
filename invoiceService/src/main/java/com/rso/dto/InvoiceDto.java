package com.rso.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.List;


@Data
public
class InvoiceDto {

    private Long id;
    private SimpleDateFormat date;
    private Long supplierId;
    private Long consumerId;
    private Long amount;
    private String otherData;

    public InvoiceDto() {}
}

@Data
public
class InvoiceListDto {
    private List<InvoiceDto> invoices;
}
