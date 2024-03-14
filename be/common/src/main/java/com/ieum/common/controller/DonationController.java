package com.ieum.common.controller;

import com.ieum.common.response.DonationHistoryResponseDTO;
import com.ieum.common.response.FundingOngoingInfoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donation")
@Tag(name = "donation", description = "donation API - 목업")
public class DonationController {

    @Operation(summary = "기부 내역 조회", description = "특정 기부 내역을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "기부 내역 조회 성공 - 사용자의 기부 내역 반환")
    @GetMapping("/{historyId}")
    public ResponseEntity<DonationHistoryResponseDTO> getDonationHistory(@PathVariable("historyId") String historyId)
        throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        DonationHistoryResponseDTO response = DonationHistoryResponseDTO.builder()
            .fundingId(1L)
            .fundingTitle("btc 아동센터에 기부")
            .facilityName("인천")
            .historyDate(sdf.parse("2023-03-02"))
            .nickname("기부니")
            .donationAmount(500)
            .fundingSummary("이것저것 외 14개")
            .build();

        return ResponseEntity.ok(response);
    }

}
