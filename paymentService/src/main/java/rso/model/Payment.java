package rso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Offer offer;

    private StatusType status;

    public Payment(Date paymentDate, StatusType status) {
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public Payment() {}

    public void setId(long id){
        this.id = id;
    }

    public long getId(){
        return this.id;
    }

    public void setPaymentDate(Date paymentDate){
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate(){
        return this.paymentDate;
    }

    public void setOffer(Offer offer){
        this.offer= offer;
    }

    public Offer getOffer(){
        return this.offer;
    }

    public void setStatus(StatusType status){
        this.status = status;
    }

    public StatusType getStatus(){
        return this.status;
    }
}
