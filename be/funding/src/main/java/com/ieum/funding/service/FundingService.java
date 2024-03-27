package com.ieum.funding.service;

import com.ieum.funding.domain.Funding;
import com.ieum.funding.domain.FundingMembers;
import com.ieum.funding.domain.FundingProducts;
import com.ieum.funding.domain.SponsorProducts;
import com.ieum.funding.dto.FundingDetailBaseDTO;
import com.ieum.funding.dto.FundingInfoDTO;
import com.ieum.funding.dto.FundingMemberDTO;
import com.ieum.funding.dto.FundingProductDTO;
import com.ieum.funding.repository.FundingMembersRepository;
import com.ieum.funding.repository.FundingProductsRepository;
import com.ieum.funding.repository.FundingRepository;
import com.ieum.funding.repository.SponsorProductsRepository;
import com.ieum.funding.response.AutoFundingResultResponseDTO;
import com.ieum.funding.response.FundingInfoResponseDTO;
import com.ieum.funding.response.FundingDetailResponseDTO;
import com.ieum.funding.response.FundingReceiptResponseDTO;
import com.ieum.funding.response.FundingReceiptResponseFromFDTO;
import com.ieum.funding.response.FundingSummaryResponseDTO;
import com.ieum.funding.response.FundingResultResponseDTO;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FundingService {

    private final FundingRepository fundingRepository;
    private final FundingMembersRepository fundingMembersRepository;
    private final FundingProductsRepository fundingProductsRepository;
    private final SponsorProductsRepository sponsorProductsRepository;

    public List<FundingSummaryResponseDTO> getFundingOngoingList() {
        List<FundingSummaryResponseDTO> ongoingFundingInfo = fundingRepository.findOngoingFundingList();
        // 로그 출력
        for (FundingSummaryResponseDTO info : ongoingFundingInfo) {
            log.info("Funding Info: {}", info);
        }
        return ongoingFundingInfo;
    }

    public List<FundingSummaryResponseDTO> getFundingCompleteList() {
        List<FundingSummaryResponseDTO> ongoingFundingInfo = fundingRepository.findCompleteFundingList();
        // 로그 출력
        for (FundingSummaryResponseDTO info : ongoingFundingInfo) {
            log.info("Funding Info: {}", info);
        }
        return ongoingFundingInfo;
    }

    public FundingDetailResponseDTO getFundingDetail(Long fundingId,
                                                     Long memberId) {
        FundingDetailBaseDTO detail = fundingRepository.findFundingDetail(fundingId);
        List<FundingMemberDTO> members = fundingMembersRepository.findByFundingId(fundingId);
        Optional<FundingMembers> link = fundingMembersRepository.findFirstByFundingIdAndMemberId(fundingId, memberId);
        Boolean isLinked = false;
        if (link.isPresent()) {
            isLinked = link.get().getAutoFundingStatus();
        }
        List<FundingProductDTO> products = fundingProductsRepository.findFundingProductDTOByFundingId(fundingId);

        return FundingDetailResponseDTO.builder()
                                       .fundingId(detail.getFundingId())
                                       .facilityName(detail.getFacilityName())
                                       .facilityAddress(detail.getFacilityAddress())
                                       .facilityPhoneNumber(detail.getFacilityPhoneNumber())
                                       .facilityRepresentativeName(detail.getFacilityRepresentativeName())
                                       .facilityRepresentativePhoneNumber(detail.getFacilityRepresentativePhoneNumber())
                                       .facilityCapacity(detail.getFacilityCapacity())
                                       .facilityImage(detail.getFacilityImage())
                                       .fundingOpenDate(detail.getFundingOpenDate())
                                       .fundingFinishDate(detail.getFundingFinishDate())
                                       .fundingPeopleCnt(detail.getFundingPeopleCnt())
                                       .fundingTitle(detail.getFundingTitle())
                                       .goalAmount(detail.getGoalAmount())
                                       .currentAmount(detail.getCurrentAmount())
                                       .content(detail.getContent())
                                       .currentLink(isLinked) // 이 값은 실제 상황에 맞게 설정해야 합니다.
                                       .people(members) // 이 값도 실제 데이터에 맞게 설정해야 합니다.
                                       .products(products)
                                       .build();
    }

    public void linkupFunding(Long fundingId, Long memberId) {
        fundingMembersRepository.unlinkAll(memberId);
        fundingMembersRepository.linkup(fundingId, memberId);
    }

    public void unlinkFunding(Long fundingId, Long memberId) {
        fundingMembersRepository.unlink(fundingId, memberId);
    }

    public FundingInfoResponseDTO getDonationInfo(Long fundingId) {
        FundingInfoDTO fundingInfo = fundingRepository.getDonationInfo(fundingId);

        return FundingInfoResponseDTO.builder()
                                     .fundingId(fundingInfo.getFundingId())
                                     .amount(fundingInfo.getAmount())
                                     .facilityName(fundingInfo.getFacilityName())
                                     .build();
    }

    // 시설명, 시설
    public FundingResultResponseDTO getFacilityInfo(Long fundingId) {
        return fundingRepository.getFacilityInfo(fundingId);
    }

    public Boolean directDonation(Long fundingId, Long memberId, Integer amount) {
        // 펀딩에 current_amount 증가
        Funding checkFunding = fundingRepository.findByFundingId(fundingId);
        // 기부 가능 여부 체크
        if (checkFunding.getCurrentAmount() >= amount) {
            fundingRepository.updateFunding(fundingId, amount);
            fundingMembersRepository.updateFundingMember(fundingId, memberId, amount);

            // 펀딩 완료 체크
            if (checkFunding.getCurrentAmount() + amount == checkFunding.getGoalAmount()) {

                fundingRepository.updateFinishDate(fundingId);
                // funding_finish_date 현재시간으로 설정
                fundingMembersRepository.unlinkAllByFundingId(fundingId);
            }

            return true;
        }
        return false;
        // 성공여부
    }

    // 자동기부 정보 요청

    // 자동기부 수행
    public AutoFundingResultResponseDTO autoDonation(Long fundingId, Long memberId, Integer amount) {
        // 펀딩에 current_amount 증가
        Funding checkFunding = fundingRepository.findByFundingId(fundingId);
        // 기부 가능 여부 체크
        if (Objects.equals(checkFunding.getCurrentAmount(), checkFunding.getGoalAmount())) {
            return AutoFundingResultResponseDTO.builder()
                                               .amount(0)
                                               .build();

        } else if (checkFunding.getGoalAmount() - checkFunding.getCurrentAmount() < amount) {
            amount = checkFunding.getGoalAmount() - checkFunding.getCurrentAmount();
        }
        // 펀딩 금액 증가
        fundingRepository.updateFunding(fundingId, amount);
        fundingMembersRepository.updateFundingMember(fundingId, memberId, amount);

        // 완료 체크
        if (checkFunding.getCurrentAmount() + amount == checkFunding.getGoalAmount()) {
            fundingRepository.updateFinishDate(fundingId);
            // funding_finish_date 현재시간으로 설정
            fundingMembersRepository.unlinkAllByFundingId(fundingId);
            // 모든 멤버 언링크
        }
        return AutoFundingResultResponseDTO.builder()
                                           .amount(amount)
                                           .build();
        // 기부 금액 반환
    }

    public FundingInfoResponseDTO getAutoDonationInfo(Long memberId) {
        return fundingRepository.getAutoDonationInfo(memberId);
    }

    public FundingReceiptResponseDTO getReceiptInfo(Long fundingId) {
        FundingReceiptResponseFromFDTO fInfo = fundingRepository.getReceiptInfo(fundingId);
        List<FundingProducts> productList = fundingProductsRepository.findByFundingId(fundingId);
        Long spId = productList.get(0).getSponsorProductId();
        SponsorProducts sp = sponsorProductsRepository.findFirstBySponsorProductId(spId);

        String productName = sp.getProductName() + "외" + Integer.toString(productList.size() -1) + "개";
        return FundingReceiptResponseDTO.builder()
            .facilityName(fInfo.getFacilityName())
            .fundingTitle(fInfo.getFundingTitle())
            .productName(productName)
            .build();
    }
}
