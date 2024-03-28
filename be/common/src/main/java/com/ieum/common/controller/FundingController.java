package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.dto.feign.funding.request.FundingDonationRequestDTO;
import com.ieum.common.dto.feign.funding.response.AutoFundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.CurrentFundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingDonationResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingInfoResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingReceiptResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.request.FundingLinkRequestDTO;
import com.ieum.common.dto.feign.funding.response.FundingDetailResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingSummaryResponseDTO;
import com.ieum.common.dto.request.DirectlyDonationRequestDTO;
import com.ieum.common.dto.request.FundingLinkupRequestDTO;
import com.ieum.common.dto.response.DirectlyDonationInfoResponseDTO;
import com.ieum.common.dto.response.DirectlyDonationResponseDTO;
import com.ieum.common.dto.response.DonationDirectlyResponseDTO;
import com.ieum.common.dto.response.ReceiptResponseDTO;
import com.ieum.common.feign.FundingFeignClient;
import com.ieum.common.format.code.SuccessCode;
import com.ieum.common.format.response.ResponseTemplate;
import com.ieum.common.service.FundingService;
import com.ieum.common.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "funding", description = "Funding API - 목업")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/funding")
@CrossOrigin("*")
@Slf4j
public class FundingController {

    private final ResponseTemplate response;

    private final FundingFeignClient fundingFeignClient;
    private final FundingService fundingService;
    private final PayService payService;

    @Operation(summary = "펀딩 상세 조회", description = "펀딩의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 상세 정보 조회 성공")
    @GetMapping("/{fundingId}/complete")
    public ResponseEntity<?> getFundingCompleteDetail(@PathVariable("fundingId") Long fundingId,
                                                      @CurrentMemberId Long memberId) {

        FundingDetailResponseDTO res = fundingService.getFundingDetail(fundingId, memberId);
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "진행 중인 펀딩 목록 조회", description = "현재 진행 중인 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "진행 중인 펀딩 목록 조회 성공")
    @GetMapping("/list/ongoing")
    public ResponseEntity<?> getFundingOngoingList() {
        List<FundingSummaryResponseDTO> res = fundingService.getFundingOngoingList();
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "완료된 펀딩 목록 조회", description = "완료된 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "완료된 펀딩 목록 조회 성공")
    @GetMapping("/list/complete")
    public ResponseEntity<?> getFundingCompleteList() {
        List<FundingSummaryResponseDTO> res = fundingService.getFundingCompleteList();
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "펀딩 연계", description = "사용자를 특정 펀딩에 연계시킵니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 연계 성공")
    @PostMapping("/linkup")
    public Boolean fundingLinkup(@RequestBody FundingLinkupRequestDTO request,
                                 @CurrentMemberId Long memberId) {
        log.info(memberId.toString());
        FundingLinkRequestDTO req = FundingLinkRequestDTO.builder()
                                                         .fundingId(request.getFundingId())
                                                         .memberId(memberId)
                                                         .build();
        return fundingService.fundingLinkup(req);
    }

    @Operation(summary = "펀딩 연계 해제", description = "사용자를 특정 펀딩의 연계를 해제시킵니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 연계 해제 성공")
    @PostMapping("/unlink")
    public Boolean fundingUnlink(@RequestBody FundingLinkupRequestDTO request,
                                 @CurrentMemberId Long memberId) {
        FundingLinkRequestDTO req = FundingLinkRequestDTO.builder()
                                                         .fundingId(request.getFundingId())
                                                         .memberId(memberId)
                                                         .build();
        return fundingService.fundingUnlink(req);
    }

    @Operation(summary = "펀딩 연동 결과 조회", description = "펀딩 연동 결과를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 연동 결과 조회 성공")
    @GetMapping("/link/result/{fundingId}")
    public ResponseEntity<?> getFundingResult(@PathVariable("fundingId") Long fundingId) {
        FundingResultResponseDTO res = fundingService.getFundingResult(fundingId);
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "직접 기부", description = "펀딩에 직접 기부합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 기부 성공 - 펀딩ID 반환")
    @PostMapping("/donation")
    public ResponseEntity<?> donationDirectly(@RequestBody DirectlyDonationRequestDTO request,
        @CurrentMemberId Long memberId) {
        DirectlyDonationResponseDTO res = fundingService.donationDirectly(request, memberId);
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "직접기부 결제 정보 요청", description = "직접기부 결제시 해당 결제에 대한 정보 요청")
    @ApiResponse(responseCode = "200", description = "정보 조회 성공")
    @GetMapping("/info/directly/{fundingId}")
    public ResponseEntity<?> getDirectlyFundingInfo(@PathVariable("fundingId") Long fundingId,
            @CurrentMemberId Long memberId) {
        FundingInfoResponseDTO funding = fundingService.getDirectlyFundingInfo(fundingId);
        int paymoney = payService.nowMyPaymoney(memberId);
        DirectlyDonationInfoResponseDTO res = DirectlyDonationInfoResponseDTO.builder()
            .fundingId(fundingId)
            .facilityName(funding.getFacilityName())
            .amount(funding.getAmount())
            .paymoneyAmount(paymoney)
            .build();
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "영수증", description = "영수증 정보")
    @ApiResponse(responseCode = "200", description = "영수증")
    @GetMapping("/receipt/{historyId}")
    public ResponseEntity<?> getReceiptInfo(@PathVariable("historyId") Long historyId,
        @CurrentMemberId Long memberId) {
        ReceiptResponseDTO res = fundingService.getReceiptInfo(historyId, memberId);
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "현재 정보 요청", description = "현재 정보 (기부 관련)")
    @ApiResponse(responseCode = "200", description = "기부 관련 현재 정보")
    @GetMapping("/info/current")
    public ResponseEntity<?> getCurrentInfo(@CurrentMemberId Long memberId) {
        CurrentFundingResultResponseDTO res = fundingService.getCurrentInfo(memberId);
        return response.success(res, SuccessCode.SUCCESS);
    }

    @Operation(summary = "직접 기부 결과 조회", description = "직접 기부 결과를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "직접 기부 결과 조회 성공")
    @GetMapping("/donation/directly/result/{history}")
    public ResponseEntity<?> getDirectlyResult(@PathVariable("history") Long history) {
        DonationDirectlyResponseDTO res = fundingService.getDirectlyResult(history);
        return response.success(res, SuccessCode.SUCCESS);
    }
}
