package rso.dto;

import rso.model.Offer;
import rso.model.StatusType;

import java.text.ParseException;
import java.util.Date;

public class PaymentDto {
    private long id;

    private StatusType status;

    private Date paymentDate;

    private OfferDto offer;

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

    public void setOffer(OfferDto offer){
        this.offer= offer;
    }

    public OfferDto getOffer(){
        return this.offer;
    }
}
