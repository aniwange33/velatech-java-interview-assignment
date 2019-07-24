package com.amos.velatechjavainterviewassigment.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
public class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String iin;
    private String scheme;
    private String bank;
    private int  stats;
    public String getStats(){
        return "\""+iin+"\"" +" : "+ stats;
    }


}
