package com.rso.dto;

import com.rso.model.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferEditDto {

    @Setter
    @Getter
    private StatusType status;

}