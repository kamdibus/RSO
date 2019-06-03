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

    private long supplierId;

    private long consumerId;

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

    public void setSupplierId(long supplierId){
        this.supplierId = supplierId;
    }

    public long getSupplierId(){
        return this.supplierId;
    }

    public void setConsumerId(long consumerId){
        this.consumerId = consumerId;
    }

    public long getConsumerId(){
        return this.consumerId;
    }
    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }
}
