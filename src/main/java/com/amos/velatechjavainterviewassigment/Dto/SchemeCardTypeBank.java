package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Data;

@Data
public class SchemeCardTypeBank {
    private  String scheme;
    private String  type;
    private String  bank;

// this fields  can be null in a situation where the card is not valid;
    private SchemeCardTypeBank(String scheme, String type, String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }

// for that there is no need to check if the any of the fields is null;
    public static SchemeCardTypeBank from (String scheme, String type, String bank) {
        return new SchemeCardTypeBank(scheme, type, bank);
    }
}
