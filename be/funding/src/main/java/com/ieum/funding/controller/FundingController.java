package com.ieum.funding.controller;

import com.ieum.funding.response.FundingDetailResponseDTO;
import com.ieum.funding.response.FundingInfoResponseDTO;
import com.ieum.funding.service.FundingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funding")
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @Operation(summary = "진행중 펀딩 상세 조회", description = "선택한 펀딩의 상세 정보를 조회")
    @GetMapping("/{fundingId}/{memberId}/ongoing")
    public ResponseEntity<FundingDetailResponseDTO> getFundingOngoingDetail(
        @PathVariable("fundingId") Long fundingId,
        @PathVariable("memberId") Long memberId) {
        FundingDetailResponseDTO response = fundingService.getFundingDetail(fundingId, memberId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "완료 펀딩 상세 조회", description = "선택한 펀딩의 상세 정보를 조회")
    @GetMapping("/{fundingId}/{memberId}/complete")
    public ResponseEntity<FundingDetailResponseDTO> getFundingCompleteDetail(
        @PathVariable("fundingId") Long fundingId,
        @PathVariable("memberId") Long memberId) {
        FundingDetailResponseDTO response = fundingService.getFundingDetail(fundingId, memberId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "진행 중인 펀딩 목록 조회", description = "현재 진행 중인 모든 펀딩의 목록을 조회합니다.")
    @GetMapping("/list/ongoing")
    public ResponseEntity<List<FundingInfoResponseDTO>> getFundingOngoingList() {
        List<FundingInfoResponseDTO> response = fundingService.getFundingOngoingList();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "진행 중인 펀딩 목록 조회", description = "현재 진행 중인 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "진행 중인 펀딩 목록 조회 성공")
    @GetMapping("/list/complete")
    public ResponseEntity<List<FundingInfoResponseDTO>> getFundingCompleteList() {
        List<FundingInfoResponseDTO> response = fundingService.getFundingCompleteList();
        return ResponseEntity.ok(response);
    }

}
