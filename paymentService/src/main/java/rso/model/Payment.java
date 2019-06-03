package rso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Document(collection = "payments")
@Data
public class Payment {

    @Transient
    public static final String SEQUENCE_NAME = "payments_sequence";

    @Id
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
}
