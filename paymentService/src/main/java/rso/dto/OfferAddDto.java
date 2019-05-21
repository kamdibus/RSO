package rso.dto;

import rso.model.Payment;
import rso.model.StatusType;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class OfferAddDto {
    private long id;

    private float discount;

    private long invoice_id;

    private Set<Payment> payments;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setInvoice_id(long invoice_id){
        this.invoice_id = invoice_id;
    }

    public long getInvoice_id(){
        return this.invoice_id;
    }

    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }
}
