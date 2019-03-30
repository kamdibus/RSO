package com.rso.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    public Payment() {}

    public Payment(final long id) {
        this.id = id;
    }
}
