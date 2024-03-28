package com.ieum.common.service;

import com.ieum.common.domain.Members;
import com.ieum.common.dto.feign.funding.dto.FundingMemberDTO;
import com.ieum.common.dto.feign.funding.request.FundingDonationRequestDTO;
import com.ieum.common.dto.feign.funding.request.FundingLinkRequestDTO;
import com.ieum.common.dto.feign.funding.response.AutoFundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.CurrentFundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingDetailResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingDonationResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingInfoResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingReceiptResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingSummaryResponseDTO;
import com.ieum.common.dto.feign.pay.response.FundingDonationResultResponseDTO;
import com.ieum.common.dto.response.DonationDirectlyResponseDTO;
import com.ieum.common.dto.response.ReceiptResponseDTO;
import com.ieum.common.feign.FundingFeignClient;
import com.ieum.common.feign.PayFeignClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FundingService {
    private final FundingFeignClient fundingFeignClient;
    private final PayFeignClient payFeignClient;
    private final MemberService memberService;

    // 펀딩 상세 조회
    public FundingDetailResponseDTO getFundingDetail(Long fundingId, Long memberId) {
        FundingDetailResponseDTO detail = fundingFeignClient.getFundingDetail(fundingId, memberId);
        List<FundingMemberDTO> members = detail.getPeople();
        for (FundingMemberDTO member : members) {
            Long memberIdFromList = member.getMemberId();
            log.info(Long.toString(memberIdFromList));
            Members getMember = memberService.findMemberById(memberIdFromList);
            member.setGradeCode(getMember.getGradeCode().getCode());
        }
        detail.setPeople(members);
        return detail;
    }

    // 진행 중인 펀딩 목록 조회
    public List<FundingSummaryResponseDTO> getFundingOngoingList() {
        return fundingFeignClient.getFundingOngoingList();
    }

    // 완료 중인 펀딩 목록 조회
    public List<FundingSummaryResponseDTO> getFundingCompleteList() {
        return fundingFeignClient.getFundingCompleteList();
    }

    // 펀딩 연동
    public Boolean fundingLinkup(FundingLinkRequestDTO request) {
        return fundingFeignClient.linkup(request);
    }

    // 펀딩 연동 해제
    public Boolean fundingUnlink(FundingLinkRequestDTO request) {
        return fundingFeignClient.unlink(request);
    }

    // 펀딩 결과 조회
    public FundingResultResponseDTO getFundingResult(Long fundingId) {
        return fundingFeignClient.getPaymentResult(fundingId);
    }

    // 직접 기부 결제 정보 요청
    public FundingInfoResponseDTO getDirectlyFundingInfo(Long fundingId) {
        return fundingFeignClient.getDirectlyFundingInfo(fundingId);
    }

    // 직접 기부
    public FundingDonationResponseDTO donationDirectly(FundingDonationRequestDTO request) {
        return fundingFeignClient.donationDirectly(request);
    }

    // 자동 기부 결제 정보 요청
    public FundingInfoResponseDTO getAutoFundingInfo(Long memberId) {
        return fundingFeignClient.getAutoFundingInfo(memberId);
    }

    // 자동 기부
    public AutoFundingResultResponseDTO donationAuto (FundingDonationRequestDTO request) {
        return fundingFeignClient.donationAuto(request);
    }

    // 영수증
    public ReceiptResponseDTO getReceiptInfo (Long historyId, Long memberId) {
        FundingDonationResultResponseDTO history = payFeignClient.getDonationHistory(historyId);
        FundingReceiptResponseDTO funding = fundingFeignClient.getReceipt(history.getFundingId());
        Members member = memberService.findMemberById(memberId);

        return ReceiptResponseDTO.builder()
            .fundingId(history.getFundingId())
            .fundingTitle(funding.getFundingTitle())
            .facilityName(funding.getFacilityName())
            .historyDate(history.getHistoryDate())
            .name(member.getName())
            .donationAmount(history.getDonationAmount())
            .fundingSummary(funding.getProductName())
            .build();
    }

    public CurrentFundingResultResponseDTO getCurrentInfo(Long memberId) {
        return fundingFeignClient.getCurrentInfo(memberId);
    }

    public DonationDirectlyResponseDTO getDirectlyResult(Long historyId) {
        FundingDonationResultResponseDTO history = payFeignClient.getDonationHistory(historyId);
        FundingResultResponseDTO funding = fundingFeignClient.getPaymentResult(history.getFundingId());
        return DonationDirectlyResponseDTO.builder()
            .fundingId(history.getFundingId())
            .facilityName(funding.getFacilityName())
            .facilityTitle(funding.getFundingTitle())
            .facilityImage(funding.getFacilityImage())
            .fundingAmount(history.getDonationAmount())
            .build();

    }
}
