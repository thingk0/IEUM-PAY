package com.ieum.pay.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/card")
public class CardValidController {
    @PostMapping("/valid")
    public static long isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16)
            return -2; //잘못된 형태

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

            //카드 정보 저장

            //저장된 카드 id 반환

            return 1;   //  4917484589897107 f
        }
        return -1;
    }

}
