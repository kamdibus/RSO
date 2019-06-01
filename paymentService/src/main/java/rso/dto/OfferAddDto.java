package rso.dto;

import rso.model.Payment;
import rso.model.StatusType;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class OfferAddDto {
    private long id;

    private float discount;

    private long invoiceId;

    private long userId;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setInvoiceId(long invoiceId){
        this.invoiceId = invoiceId;
    }

    public long getInvoiceId(){
        return this.invoiceId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public long getUserId(){
        return this.userId;
    }

    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }
}
