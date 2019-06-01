package rso.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Entity
@Table(name = "OFFERS")
@EqualsAndHashCode(exclude = "payments")
public class Offer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private float discount;

    private StatusType status;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date creationDate;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date expirationDate;

    private long invoiceId;
    
    private long userId;

    @OneToMany(mappedBy="offer", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    public Offer(Date creationDate, Date expirationDate, long userId,  long invoiceId, float discount, StatusType status, Payment... payments) {
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.userId = userId;
        this.invoiceId = invoiceId;
        this.status = status;
        this.discount = discount;
        this.payments = Stream.of(payments).collect(Collectors.toSet());
        this.payments.forEach(x -> x.setOffer(this));
    }

    public Offer() {}

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }

    public Date getCreationDate(){
        return this.creationDate;
    }

    public void setExpirationDate(Date expirationDate){
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate(){
        return this.expirationDate;
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

    public float getDiscount(){
        return this.discount;
    }
}
