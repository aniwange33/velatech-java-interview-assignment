package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Value;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;

@Value
public class CardVerificationRequest {
    @NumberFormat
    @NotNull
    private long  IIN;

    private CardVerificationRequest(long IIN) {

        this.IIN = IIN;
    }

    public static CardVerificationRequest from(long IIN) throws  IllegalArgumentException {
        // IIN is the first 6 digits of the card number
        if(IIN<1000000){
            throw new IllegalArgumentException("The IIN must be upto 6 digits !!!");
        }
        return new CardVerificationRequest(IIN);
    }
}
