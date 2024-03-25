package com.ieum.pay.controller;

import com.ieum.pay.request.FundingDonationRequestDTO;
import com.ieum.pay.response.FundingDonationResultResponseDTO;
import com.ieum.pay.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funding/donation")
@RequiredArgsConstructor
public class FundingController {
    private final HistoryService historyService;
    @GetMapping("/result/{historyId}")
    public FundingDonationResultResponseDTO getDonationHistory(@PathVariable Long historyId){
        return historyService.getFundingHistory(historyId);
    }
    @PostMapping
    public Long DirectDonation(@RequestBody FundingDonationRequestDTO requestDTO){
        return historyService.directDonation(requestDTO.getMemberId(), requestDTO.getFundingId(), requestDTO.getDonationAmount());
    }
}
