package rso.dto;

import lombok.Getter;
import lombok.Setter;
import rso.model.StatusType;

@Getter
@Setter
public class OfferEditDto {

    @Setter
    @Getter
    private StatusType status;

}