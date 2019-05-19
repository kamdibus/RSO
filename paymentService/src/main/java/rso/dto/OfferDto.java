package rso.dto;

import rso.model.Payment;
import rso.model.StatusType;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OfferDto {
    private long id;

    private float discount;

    private StatusType status;

    private Date creationDate;

    private long invoiceId;

    private long userId;

    private Set<PaymentDto> payments;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = new Date();
    }

    public Date getCreationDate(){
        return this.creationDate;
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


    public void setStatus(StatusType status){
        this.status = status;
    }

    public StatusType getStatus(){
        return this.status;
    }

    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }

    public void setPayments(Set<PaymentDto> payments){

        this.payments = payments;
    }

    public Set<PaymentDto> getPayments(Set<PaymentDto> payments){
        return this.payments;
    }

}