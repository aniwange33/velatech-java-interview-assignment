package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralResponse {
    private Number number;
    private String scheme;
    private String type ;
    private String  brand;
    boolean prepaid;
    private Country country;
    private Bank bank;

    // debugging purpose;
    @Override
    public String toString() {
        return "GeneralResponse{" +
                "number=" + number +
                ", scheme='" + scheme + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", prepaid=" + prepaid +
                ", country=" + country +
                ", bank=" + bank +
                '}';
    }
}
