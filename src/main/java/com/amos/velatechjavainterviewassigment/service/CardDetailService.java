package com.amos.velatechjavainterviewassigment.service;

import com.amos.velatechjavainterviewassigment.model.CardDetail;

public interface CardDetailService {
    CardDetail insert(CardDetail cardDetail);
    CardDetail findByIin(String iin);
    CardDetail updateStats(String iin);
}
