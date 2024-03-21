package com.ieum.pay.controller;

import com.ieum.pay.request.PaymentChargeMoney;
import com.ieum.pay.request.PaymentRequestDTO;
import com.ieum.pay.service.HistoryService;
import com.ieum.pay.service.PaymoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymoneyService paymoneyService;
    private final HistoryService historyService;
    @PostMapping("chargeMoney")
    public int chargeMoney(@RequestBody PaymentChargeMoney requestDTO){
        return paymoneyService.chargeMoney(requestDTO.getMemberId(), requestDTO.getPaymentMoney());
    }
    @PostMapping
    public void payment(@RequestBody PaymentRequestDTO requestDTO){
        Long memberId = requestDTO.getMemberId();
        Long storeId = requestDTO.getStoreId();
        Long fundingId = requestDTO.getFundingId();
        Long cardId = requestDTO.getCardId();
        int chargeAmount = requestDTO.getChargeAmount();
        int amount = requestDTO.getAmount();
        int donationAmount = requestDTO.getDonationAmount();
        //charge
        paymoneyService.updatePaymonyAmount(1, memberId, chargeAmount);
        //payment
        historyService.payment(memberId,storeId,fundingId,cardId,chargeAmount,amount,donationAmount);
        //donation

        //paymoney

    }
}
