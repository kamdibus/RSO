package rso.dto;

import rso.model.Payment;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class OfferDto {
    private long id;

    private float discount;

    private String status;

    private Date creationDate;

    private long invoice_id;

    private Set<Payment> payments;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = new Date();
    }

    public Date getCreationDate(String timezone) throws ParseException {
        return this.creationDate;
    }

    public void setInvoice_id(long invoice_id){
        this.invoice_id = invoice_id;
    }

    public long getInvoice_id(){
        return this.invoice_id;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }
}
