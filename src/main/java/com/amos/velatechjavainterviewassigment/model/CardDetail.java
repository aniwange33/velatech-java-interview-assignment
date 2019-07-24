package com.amos.velatechjavainterviewassigment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "card_detail")
public class CardDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String iin;
    private String scheme;
    private String bank;
    private int  stats;

    private CardDetail(String iin, String scheme, String bank, int stats) {
        this.iin = iin;
        this.scheme = scheme;
        this.bank = bank;
        this.stats = stats;
    }

    public static CardDetail createCardDetail(String iin, String scheme, String bank, int stats) {
        return new CardDetail(iin, scheme, bank, stats);
    }

    public String getStatsDisplay(){
        return ""+iin+"" +" : "+ stats;
    }


}
