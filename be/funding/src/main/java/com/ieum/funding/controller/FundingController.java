package com.ieum.funding.controller;

import com.ieum.funding.request.DirectFundingInfoRequestDTO;
import com.ieum.funding.request.FundingLinkRequestDTO;
import com.ieum.funding.response.DirectFundingInfoResponseDTO;
import com.ieum.funding.response.FundingDetailResponseDTO;
import com.ieum.funding.response.FundingInfoResponseDTO;
import com.ieum.funding.service.FundingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funding")
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @Operation(summary = "펀딩 상세 조회", description = "선택한 펀딩의 상세 정보를 조회")
    @GetMapping("/{fundingId}/{memberId}/detail")
    public ResponseEntity<FundingDetailResponseDTO> getFundingOngoingDetail(
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

    @Operation(summary = "펀딩 연계", description = "사용자를 특정 펀딩에 연계시킵니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 연계 성공")
    @PostMapping("/linkup")
    public ResponseEntity<HttpStatus> fundingLinkup(@RequestBody FundingLinkRequestDTO request) {
        fundingService.linkupFunding(request.getFundingId(), request.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "펀딩 연계 해제", description = "특정 펀딩에 대한 연계를 해제합니다")
    @ApiResponse(responseCode = "200", description = "펀딩 연계 해제 성공")
    @PostMapping("/unlink")
    public ResponseEntity<HttpStatus> fundingUnlink(@RequestBody FundingLinkRequestDTO request) {
        fundingService.unlinkFunding(request.getFundingId(), request.getMemberId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "직접기부 결제 정보 요청", description = "직접기부 결제시 해당 결제에 대한 정보 요청")
    @ApiResponse(responseCode = "200", description = "정보 조회 성공")
    @GetMapping("/info")
    public ResponseEntity<DirectFundingInfoResponseDTO> getFundgingInfo(
        DirectFundingInfoRequestDTO requset) {
        DirectFundingInfoResponseDTO response = fundingService.getDonationInfo(
            requset.getFundingId(), requset.getMemberId());
        return ResponseEntity.ok(response);
    }

    // --------- 해야함

//    @Operation(summary = "펀딩 기부", description = "펀딩에 직접 기부합니다.")
//    @ApiResponse(responseCode = "200", description = "펀딩 기부 성공 - 펀딩ID 반환")
//    @PostMapping("/donation")
//    public ResponseEntity<FundingDonationResponseDTO> donationDirectly(
//        @RequestBody FundingDonationRequestDTO request) {
//        FundingDonationResponseDTO response = FundingDonationResponseDTO.builder()
//            .build();
//
//        return ResponseEntity.ok(response);
//    }

//    @Operation(summary = "펀딩 결과 조회(직접 기부)", description = "직접기부 시 펀딩의 결과를 조회합니다.")
//    @ApiResponse(responseCode = "200", description = "펀딩 결과 조회 성공")
//    @GetMapping("/donation/result/{fundingId}")
//    public ResponseEntity<FundingResultResponseDTO> getFundgingResult(
//        @PathVariable("fundingId") Long fundingId) {
//        FundingResultResponseDTO response = FundingResultResponseDTO.builder().build();
//
//        return ResponseEntity.ok(response);
//    }
}
