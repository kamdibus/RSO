package com.rso.dto;
import lombok.Data;
import java.text.SimpleDateFormat;


@Data
public class InvoiceEntityDto {

    private String name;
    private SimpleDateFormat date;
    // TODO: replace the data placeholder with the actual data
    private String data;

    public InvoiceEntityDto() {}
}
