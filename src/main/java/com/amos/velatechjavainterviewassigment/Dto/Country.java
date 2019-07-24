package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {
    private String numeric;
    private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private int latitude;
    private int longitude;


}
