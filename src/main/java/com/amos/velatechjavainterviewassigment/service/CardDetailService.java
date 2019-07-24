package com.amos.velatechjavainterviewassigment.service;

import com.amos.velatechjavainterviewassigment.model.CardDetail;

import java.util.List;

public interface CardDetailService {
    CardDetail insert(CardDetail cardDetail);
    CardDetail findByIin(String iin);
    CardDetail updateStats(String iin);
    List<CardDetail> findAllCardDetails();
}
