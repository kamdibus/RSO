package com.rso.dto;

import com.rso.model.Offer;
import com.rso.model.StatusType;

import java.text.ParseException;
import java.util.Date;

public class PaymentDto {
    private long id;

    private StatusType status;

    private Date paymentDate;

    private long offerId;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setCreationDate(Date paymentDate){
        this.paymentDate = new Date();
    }

    public Date getCreationDate(String timezone) throws ParseException {
        return this.paymentDate;
    }

    public void setStatus(StatusType status){
        this.status = status;
    }

    public StatusType getStatus(){
        return this.status;
    }

    public void setOfferId(Offer offer){
        this.offerId= offer.getId();
    }

    public long getOfferId(){
        return this.offerId;
    }
}
