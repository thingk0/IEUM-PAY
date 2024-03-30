package com.ieum.pay.controller;

import com.ieum.pay.domain.DonationHistories;
import com.ieum.pay.domain.Histories;
import com.ieum.pay.request.CardValidRequestDTO;
import com.ieum.pay.response.DonationReceiptResponseDTO;
import com.ieum.pay.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/donation")
@RequiredArgsConstructor
public class DonationController {
    private final HistoryService historyService;

    @GetMapping("/{memberId}/{historyId}")
    public DonationReceiptResponseDTO donationReceipt (@PathVariable("memberId") Long memberId, @PathVariable("historyId") Long historyId){
        Histories history = historyService.getHistoryEntity(historyId);
        DonationHistories donationHistory = historyService.getDonationHistory(memberId,historyId);
        if(donationHistory == null)
            return null;
        return DonationReceiptResponseDTO.builder()
                .fundingId(donationHistory.getFundingId())
                .historyDate(history.getHistoryDate())
                .donationAmount(donationHistory.getDonationAmount())
                .build();
    }
}
