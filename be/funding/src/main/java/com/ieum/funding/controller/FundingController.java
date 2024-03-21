package com.ieum.funding.controller;

import com.ieum.funding.response.FundingOngoingInfoResponseDTO;
import com.ieum.funding.service.FundingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funding")
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;
    @Operation(summary = "진행 중인 펀딩 목록 조회", description = "현재 진행 중인 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "진행 중인 펀딩 목록 조회 성공")
    @GetMapping("/list/ongoing")
    public ResponseEntity<List<FundingOngoingInfoResponseDTO>> getFundingOngoingList () {
        List<FundingOngoingInfoResponseDTO> response = fundingService.getFundingOngoingList();


//             .fundingId(1L)
//            .facilityName("btc")
//            .fundingTitle("과자를 사주세요")
//            .fundingOpenDate(sdf.parse("2023-02-02"))
//            .fundingFinishDate(sdf.parse("2023-03-02"))
//            .facilityImage("http:FDSAFSDAFDSAF")
//            .fundingPeopleCnt(10)
//            .goalAmount(500000)
//            .currentAmount(1000)

        return ResponseEntity.ok(response);
    }

}
