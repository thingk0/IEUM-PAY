package com.ieum.pay.controller;

import com.ieum.pay.request.RemittanceRequestDTO;
import com.ieum.pay.service.HistoryService;
import com.ieum.pay.service.PaymoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remittance")
@RequiredArgsConstructor
public class RemittanceController {

    private final HistoryService historyService;
    private final PaymoneyService paymoneyService;
    @PostMapping
    public Long remittancePaymoney(@RequestBody RemittanceRequestDTO requestDTO){
        int moveMoney = requestDTO.getAmount();
        // paymoney 충전 체크
        int chargeMoney = paymoneyService.chargeMoney(requestDTO.getSenderId(), moveMoney);
        // charge
        paymoneyService.updatePaymonyAmount(1, requestDTO.getSenderId(),chargeMoney);

        //sender의 history + pay money update
        Long historyId = historyService.sendMoney(requestDTO.getSenderId(),requestDTO.getReceiverName(),moveMoney, chargeMoney, requestDTO.getCardId());
        paymoneyService.updatePaymonyAmount(-1, requestDTO.getSenderId(), moveMoney);

        //receiver의 history
        historyService.receiveMoney(requestDTO.getReceiverId(), requestDTO.getSenderName(), moveMoney);
        paymoneyService.updatePaymonyAmount(1, requestDTO.getReceiverId(), moveMoney);

        return historyId;
    }
}
