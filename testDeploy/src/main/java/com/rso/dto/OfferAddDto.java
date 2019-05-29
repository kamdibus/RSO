package com.rso.dto;

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
