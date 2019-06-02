package rso.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document(collection = "offers")
@Data
@EqualsAndHashCode(exclude = "payments")
public class Offer {

    @Transient
    public static final String SEQUENCE_NAME = "offers_sequence";

    @Id
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
}
