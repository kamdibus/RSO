package com.rso.dto;

import com.rso.model.StatusType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class OfferDto {

    @Setter
    @Getter
    private long id;

    private float discount;

    @Setter
    @Getter
    private StatusType status;

    @Setter
    @Getter
    private Date creationDate;

    @Setter
    @Getter
    private Date expirationDate;

    @Setter
    @Getter
    private long invoiceId;

    @Setter
    @Getter
    private long userId;

    @Setter
    @Getter
    private Set<PaymentDto> payments;

    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }

}