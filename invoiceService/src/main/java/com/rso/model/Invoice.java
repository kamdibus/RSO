package com.rso.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String data;

    public Invoice(long id, final String data) {
        this.id = id;
        this.data = data;
    }

    public Invoice() {}

}
