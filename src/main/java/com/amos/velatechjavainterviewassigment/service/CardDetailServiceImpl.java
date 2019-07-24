package com.amos.velatechjavainterviewassigment.service;

import com.amos.velatechjavainterviewassigment.model.CardDetail;
import com.amos.velatechjavainterviewassigment.repository.CardDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardDetailServiceImpl  implements CardDetailService {
    @Autowired
    private  CardDetailRepository cardDetailRepository;

    @Override
    public CardDetail insert(CardDetail cardDetail) {
        return cardDetailRepository.save(cardDetail);
    }

    @Override
    public CardDetail findByIin(Long iin) {
        return  cardDetailRepository.findByIin(iin);
    }

    @Override
    public CardDetail updateStats(Long iin) {
        CardDetail cardDetail=cardDetailRepository.findByIin(iin);
        cardDetail.setStats(Integer.valueOf(cardDetail.getStats())+1);
        return cardDetailRepository.save(cardDetail);
    }

    @Override
    public List<CardDetail> findAllCardDetails() {
        return cardDetailRepository.findAll();
    }
}
