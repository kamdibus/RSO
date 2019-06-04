package com.rso.dto;

import lombok.Data;

@Data
public
class InvoiceDto {

    private Long id;

    private String date;

    private Long supplierId;

    private Long consumerId;

    private Long amount;

    private String otherData;
}

