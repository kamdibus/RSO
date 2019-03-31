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

    private float amount;

    public Payment(final float amount) {
        this.amount = amount;
    }

    public Payment() {}

}
