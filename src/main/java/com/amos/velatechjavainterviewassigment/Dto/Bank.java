package com.amos.velatechjavainterviewassigment.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bank {
    private String name;
    private String url;
    private String phone;
    private String city;

}
