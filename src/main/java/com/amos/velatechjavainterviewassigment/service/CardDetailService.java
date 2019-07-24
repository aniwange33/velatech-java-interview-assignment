package com.amos.velatechjavainterviewassigment.service;

import com.amos.velatechjavainterviewassigment.model.CardDetail;

import java.util.List;

public interface CardDetailService {
    CardDetail insert(CardDetail cardDetail);
    CardDetail findByIin(Long iin);
    CardDetail updateStats(Long iin);
    List<CardDetail> findAllCardDetails();
}
