package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.dto.request.FundingDonationRequestDTO;
import com.ieum.common.dto.request.FundingLinkupRequestDTO;
import com.ieum.common.dto.response.FundingDonationResponseDTO;
import com.ieum.common.dto.response.FundingInfoResponseDTO;
import com.ieum.common.dto.response.FundingResultResponseDTO;
import com.ieum.common.dto.tmpdto.funding.request.FundingLinkRequestDTO;
import com.ieum.common.dto.tmpdto.funding.response.FundingDetailResponseDTO;
import com.ieum.common.dto.tmpdto.funding.response.FundingSummaryResponseDTO;
import com.ieum.common.feign.FundingFeignClient;
import com.ieum.common.format.response.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "funding", description = "Funding API - 목업")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/funding2")
public class FundingController {

    private final ResponseTemplate response;

    private final FundingFeignClient fundingFeignClient;

    @Operation(summary = "완료된 펀딩 상세 조회", description = "완료된 펀딩의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "완료된 펀딩 상세 정보 조회 성공")
    @GetMapping("/{fundingId}/complete")
    public ResponseEntity<?> getFundingCompleteDetail(@PathVariable("fundingId") Long fundingId,
        @CurrentMemberId Long memberId) {

        FundingDetailResponseDTO res = fundingFeignClient.getFundingDetail(fundingId, memberId);

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 기부", description = "펀딩에 직접 기부합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 기부 성공 - 펀딩ID 반환")
    @PostMapping("/donation")
    public ResponseEntity<?> donationDirectly(@RequestBody FundingDonationRequestDTO request) {
        var res = FundingDonationResponseDTO.builder()
                                            .fundingId(1L)
                                            .build();
        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 결과 조회(직접 기부)", description = "직접기부 시 펀딩의 결과를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 결과 조회 성공")
    @GetMapping("/donation/result/{fundingId}")
    public ResponseEntity<?> getFundgingResult(
        @PathVariable("fundingId") Long fundingId) {
        var res = FundingResultResponseDTO.builder()
                                          .fundingTitle("떡잎어린이집 후원")
                                          .factilityName("떡잎어린이집")
                                          .facilityImage(
                                              "https://ko.wikipedia.org/wiki/%EC%8B%A0%EC%A7%B1%EA%B5%AC#/media/%ED%8C%8C%EC%9D%BC:%EC%8B%A0%EC%A7%B1%EA%B5%AC.png")
                                          .build();

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "직접기부 결제 정보 요청", description = "직접기부 결제시 해당 결제에 대한 정보 요청")
    @ApiResponse(responseCode = "200", description = "정보 조회 성공")
    @GetMapping("/info/{fundingId}/{amount}")
    public ResponseEntity<?> getFundgingInfo(@PathVariable("fundingId") Long fundingId, @PathVariable("amount") int amount) {
        var res = FundingInfoResponseDTO.builder()
                                        .fundingId(fundingId)
                                        .amount(amount)
                                        .facilityName("떡잎어린이집")
                                        .paymoneyAmount(400)
                                        .chargeAmount(10000)
                                        .build();

        return response.success(res, HttpStatus.OK);
    }



    @Operation(summary = "완료된 펀딩 목록 조회", description = "완료된 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "완료된 펀딩 목록 조회 성공")
    @GetMapping("/list/complete")
    public ResponseEntity<?> getFundingCompleteList() {
        List<FundingSummaryResponseDTO> res = fundingFeignClient.getFundingCompleteList();
        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "진행 중인 펀딩 목록 조회", description = "현재 진행 중인 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "진행 중인 펀딩 목록 조회 성공")
    @GetMapping("/list/ongoing")
    public ResponseEntity<?> getFundingOngoingList() {
        List<FundingSummaryResponseDTO> res = fundingFeignClient.getFundingOngoingList();
        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 연계", description = "사용자를 특정 펀딩에 연계시킵니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 연계 성공")
    @PostMapping("/linkup")
    public Boolean fundingLinkup(@RequestBody FundingLinkupRequestDTO request,
        @CurrentMemberId Long memberId) {
        FundingLinkRequestDTO req = FundingLinkRequestDTO.builder()
            .fundingId(request.getFundingId())
            .memberId(memberId)
            .build();
        return fundingFeignClient.linkup(req);
    }

    @PostMapping("/unlink")
    public Boolean fundingUnlink(@RequestBody FundingLinkupRequestDTO request,
        @CurrentMemberId Long memberId) {
        FundingLinkRequestDTO req = FundingLinkRequestDTO.builder()
            .fundingId(request.getFundingId())
            .memberId(memberId)
            .build();
        return fundingFeignClient.linkup(req);
    }

}
