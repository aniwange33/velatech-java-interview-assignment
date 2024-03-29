package com.amos.velatechjavainterviewassigment.controller;

import com.amos.velatechjavainterviewassigment.Dto.CardVerificationResponseJSON;
import com.amos.velatechjavainterviewassigment.Dto.GeneralResponse;
import com.amos.velatechjavainterviewassigment.Dto.StatsReport;
import com.amos.velatechjavainterviewassigment.Dto.mapper.SchemeCardTypeBankMapper;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Optional<GeneralResponse> response;
    private boolean isResponseValid;
    private boolean isCardPresent;
    private boolean cardDetailListPresent;

    @GetMapping(value = "card-scheme/verify")
    ResponseEntity<?> verifyCard(@RequestParam long IIN) {
        if (IIN < 100000) {
            return new ResponseEntity<>("Wrong IIN , IIN is the first 6 to 8 digits of your Card.", HttpStatus.BAD_REQUEST);
        }

        final String URL = "https://lookup.binlist.net/" + String.valueOf(IIN);


        try{
            response = Optional.ofNullable(restTemplateConfig.getForObject(URL, GeneralResponse.class));
        }catch (Exception errorException){
            return new ResponseEntity<>("Invalid Card", HttpStatus.NOT_FOUND);
        }

        isResponseValid = response.isPresent() && !response.toString().isEmpty();

        if (isResponseValid) {
            CardVerificationResponseJSON cardVerificationResponseJSON = getCardVerificationResponseJSON(response);
            Optional<CardDetail> cardDetail = Optional.ofNullable(cardDetailService.findByIin(String.valueOf(IIN)));
            isCardPresent = cardDetail.isPresent();

            if (isCardPresent) {
                cardDetailService.updateStats(String.valueOf(IIN));
                return new ResponseEntity<>(cardVerificationResponseJSON, HttpStatus.OK);
            }

            CardDetail cardDetail1 = CardDetail.createCardDetail(String.valueOf(IIN), response.get().getScheme(), response.get().getBank().getName(), 1);
            cardDetailService.insert(cardDetail1);
            return new ResponseEntity<>(cardVerificationResponseJSON, HttpStatus.OK);

        }

        return new ResponseEntity<>("Invalid Card", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "card-scheme/stats")
    ResponseEntity<?> getCardStats(@RequestParam int start, @RequestParam int limit) {
        Optional<List<CardDetail>> cardDetailList = Optional.ofNullable(cardDetailService.findAllCardDetails());

        cardDetailListPresent = cardDetailList.isPresent();

        if (cardDetailListPresent) {

            if(limit>cardDetailList.get().size()){
                return ResponseEntity.ok("limit "+ limit +" is more than what is in database");
            }
            StatsReport statsReport = StatsReport.createStatsReport(start, limit, cardDetailList.get().size());
            statsReport.setSuccess(true);

            Map<String, Integer> statsReportMap = new HashMap<>();
            for (int i = start - 1; i < limit; i++) {
                statsReportMap.put(cardDetailList.get().get(i).getIin(), cardDetailList.get().get(i).getStats());
            }

            statsReport.setPayload(statsReportMap);
            return new ResponseEntity<>(statsReport, HttpStatus.OK);
        }

        return ResponseEntity.ok("no content");
    }

    private CardVerificationResponseJSON getCardVerificationResponseJSON(Optional<GeneralResponse> response) {
        System.out.println("bank name  "+SchemeCardTypeBankMapper.CARD_VERIFICATION_MAPPER.schemeCardTypeBankFrom(response.get()));
        return CardVerificationResponseJSON.builder()
                .success(true)
                .payload(SchemeCardTypeBankMapper.CARD_VERIFICATION_MAPPER.schemeCardTypeBankFrom(response.get()))
                .build();
    }

}
