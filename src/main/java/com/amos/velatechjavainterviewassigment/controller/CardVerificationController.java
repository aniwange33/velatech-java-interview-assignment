package com.amos.velatechjavainterviewassigment.controller;


import com.amos.velatechjavainterviewassigment.Dto.CardVerificationResponseJSON;
import com.amos.velatechjavainterviewassigment.Dto.GeneralResponse;
import com.amos.velatechjavainterviewassigment.Dto.SchemeCardTypeBank;
import com.amos.velatechjavainterviewassigment.model.CardDetail;
import com.amos.velatechjavainterviewassigment.service.CardDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/")
public class CardVerificationController {
    // https://lookup.binlist.net/45717360
    private static final Logger log = LoggerFactory.getLogger(CardVerificationController.class);
    @Autowired
    RestTemplate restTemplateConfig;
    @Autowired
    CardDetailService cardDetailService;

    @GetMapping(value = "card-scheme/verify")
    ResponseEntity<?> verifyCard(@RequestParam long IIN){
        if(IIN<100000){
            return new ResponseEntity<>("Wrong IIN , IIN is the first 6 to 8 digits of your Card.", HttpStatus.BAD_REQUEST);
        }

        final String URL= "https://lookup.binlist.net/"+IIN;

        Optional<GeneralResponse> response =Optional.ofNullable(restTemplateConfig.getForObject(URL,GeneralResponse.class));
        if(response.isPresent() && ! response.toString().isEmpty()) {
            CardVerificationResponseJSON cardVerificationResponseJSON = getCardVerificationResponseJSON(response);
            Optional<CardDetail> cardDetail=Optional.ofNullable(cardDetailService.findByIin(IIN));
            if(cardDetail.isPresent()){
                cardDetailService.updateStats(IIN);
                return new ResponseEntity<>(cardVerificationResponseJSON, HttpStatus.OK);
            }
            CardDetail cardDetail1=CardDetail.builder()
                    .bank(response.get().getBank().getName())
                    .iin(String.valueOf(IIN))
                    .scheme(response.get().getScheme())
                    .stats(1)
                    .build();
            cardDetailService.insert(cardDetail1);
            return new ResponseEntity<>(cardVerificationResponseJSON, HttpStatus.OK);


        }

        return new ResponseEntity<>("Invalid Card", HttpStatus.NOT_FOUND);
    }



    private CardVerificationResponseJSON getCardVerificationResponseJSON(Optional<GeneralResponse> response) {
        return CardVerificationResponseJSON.builder()
                        .success(true)
                        .payload(SchemeCardTypeBank.from(response.get().getScheme(),response.get().getType(),response.get().getBank().getName()))
                        .build();
    }

    @GetMapping(value = "card-scheme/stats")
    ResponseEntity<?> verifyCard( @RequestParam int start, @RequestParam int limit){
        Optional<List<CardDetail>> cardDetailList= Optional.ofNullable(cardDetailService.findAllCardDetails());
        if(cardDetailList.isPresent()){
            return new ResponseEntity<>(cardDetailList.get(),HttpStatus.OK);
        }
        return ResponseEntity.ok("no content");
    }
}
