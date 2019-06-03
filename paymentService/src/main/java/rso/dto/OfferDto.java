package rso.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import rso.model.Payment;
import rso.model.StatusType;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class OfferDto {

    @Setter
    @Getter
    private long id;

    private float discount;

    @Setter
    @Getter
    private StatusType status;

    @Setter
    @Getter
    private Date creationDate;

    @Setter
    @Getter
    private Date expirationDate;

    @Setter
    @Getter
    private long invoiceId;

    @Setter
    @Getter

    private long supplierId;

    @Setter
    @Getter
    private long consumerId;

    @Setter
    @Getter
    private Set<PaymentDto> payments;

    public void setDiscount(float discount){
        this.discount = discount;
    }

    public float getDiscount(float discount){
        return this.discount;
    }

}