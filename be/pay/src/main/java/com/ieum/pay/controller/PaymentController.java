package com.ieum.pay.controller;

import com.ieum.pay.domain.Stores;
import com.ieum.pay.request.PaymentChargeMoney;
import com.ieum.pay.request.PaymentRequestDTO;
import com.ieum.pay.response.PaymentHistoryResponseDTO;
import com.ieum.pay.service.HistoryService;
import com.ieum.pay.service.PaymoneyService;
import com.ieum.pay.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymoneyService paymoneyService;
    private final HistoryService historyService;
    private final StoreService storeService;
    @PostMapping("chargeMoney")
    public int chargeMoney(@RequestBody PaymentChargeMoney requestDTO){
        return paymoneyService.chargeMoney(requestDTO.getMemberId(), requestDTO.getPaymentMoney());
    }
    @PostMapping
    public Long payment(@RequestBody PaymentRequestDTO requestDTO){
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
        Long historyId = historyService.payment(memberId,storeId,fundingId,cardId,chargeAmount,amount,donationAmount);
        //donation

        //paymoney
        paymoneyService.updatePaymonyAmount(-1, memberId, amount + donationAmount);

        return historyId;
    }

    @GetMapping("info/{storeId}")
    public String getStoreName(@PathVariable Long storeId){
        Stores stores = storeService.getStoreName(storeId);
        return stores.getStoreName();
    }

    @GetMapping("{historyId}")
    public PaymentHistoryResponseDTO getPaymentHistory(@PathVariable Long historyId){
        return historyService.getHistory(historyId);
    }
}
