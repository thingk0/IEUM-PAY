package com.ieum.common.service;

import com.ieum.common.dto.feign.funding.request.FundingLinkRequestDTO;
import com.ieum.common.dto.feign.funding.response.FundingDetailResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingSummaryResponseDTO;
import com.ieum.common.feign.FundingFeignClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FundingService {
    private final FundingFeignClient fundingFeignClient;

    // 펀딩 상세 조회
    public FundingDetailResponseDTO getFundingDetail(Long fundingId, Long memberId) {
        return fundingFeignClient.getFundingDetail(fundingId, memberId);
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

    // 직접 기부 결제 정보 요청

    // 직접 기부

    // 자동 기부 결제 정보 요청

    // 자동 기부

    // 펀딩 결과 조회
    public FundingResultResponseDTO getFundingResult(Long fundingId) {
        return fundingFeignClient.getPaymentResult(fundingId);
    }
}
