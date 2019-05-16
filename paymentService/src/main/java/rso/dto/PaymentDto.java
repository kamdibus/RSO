package rso.dto;

import java.text.ParseException;
import java.util.Date;

public class PaymentDto {
    private long id;

    private String status;

    private Date creationDate;

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

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

}
