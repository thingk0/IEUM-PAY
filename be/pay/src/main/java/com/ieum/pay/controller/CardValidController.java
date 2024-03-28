package com.ieum.pay.controller;

import com.ieum.pay.domain.Cards;
import com.ieum.pay.request.CardRegisterRequestDTO;
import com.ieum.pay.request.CardValidRequestDTO;
import com.ieum.pay.service.CardService;
import com.ieum.pay.service.RegisteredCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
public class CardValidController {

    private final CardService cardService;
    private final RegisteredCardService registeredCardService;
    @PostMapping("/valid")
    public Long isValidCardNumber(@RequestBody CardRegisterRequestDTO requestDTO) {
        String cardNumber = requestDTO.getCardNumber();
        if (cardNumber == null || cardNumber.length() != 16)
            return -2L; //잘못된 형태

        char[] cardNumberArray = cardNumber.toCharArray();
        int lastNumber = cardNumberArray[15] - '0';

        int sum = 0;
        // Luhn algorithm
        for (int i = 0; i < 15; i++) {
            int num = cardNumberArray[i] - '0';
            if (i % 2 == 0) {
                num = num * 2;
                if (num > 9)
                    num = num - 9;
            }
            sum += num;
        }

        sum += lastNumber;
        if(sum % 10 == 0) {
            //카드 번호를 이용하여 카드 회사 값 get
            Cards card = cardService.findCard(cardNumber);

            //카드 정보 저장
            //저장된 카드 id 반환
            return registeredCardService.registerCard(card, requestDTO.getMemberId(),requestDTO.getCardNickname());
            //  4917484589897107 f
        }
        return -1L;
    }

    @PutMapping("/delete")
    public boolean deleteCard(@RequestBody CardValidRequestDTO requestDTO){
        //card delete
        return cardService.delete(requestDTO.getMemberId(),requestDTO.getRegisteredCardId());
    }

    @GetMapping("{registeredCardId}")
    public String getCardName(@PathVariable("registeredCardId") Long registeredCardId){
        return cardService.getCardName(registeredCardId);
    }

}
