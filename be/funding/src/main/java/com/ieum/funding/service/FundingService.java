package com.ieum.funding.service;

import com.ieum.funding.domain.Funding;
import com.ieum.funding.domain.FundingMembers;
import com.ieum.funding.domain.Paymoney;
import com.ieum.funding.dto.FundingInfoDTO;
import com.ieum.funding.dto.FundingMemberDTO;
import com.ieum.funding.dto.FundingProductDTO;
import com.ieum.funding.repository.FundingMembersRepository;
import com.ieum.funding.repository.FundingProductsRepository;
import com.ieum.funding.repository.FundingRepository;
import com.ieum.funding.repository.PaymoneyRepository;
import com.ieum.funding.response.DirectFundingInfoResponseDTO;
import com.ieum.funding.response.FundingDetailResponseDTO;
import com.ieum.funding.dto.FundingDetailBaseDTO;
import com.ieum.funding.response.FundingInfoResponseDTO;
import java.util.List;
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
    private final PaymoneyRepository paymoneyRepository;

    public List<FundingInfoResponseDTO> getFundingOngoingList() {
        List<FundingInfoResponseDTO> ongoingFundingInfo = fundingRepository.findOngoingFundingList();
        // 로그 출력
        for (FundingInfoResponseDTO info : ongoingFundingInfo) {
            log.info("Funding Info: {}", info);
        }
        return ongoingFundingInfo;
    }

    public List<FundingInfoResponseDTO> getFundingCompleteList() {
        List<FundingInfoResponseDTO> ongoingFundingInfo = fundingRepository.findCompleteFundingList();
        // 로그 출력
        for (FundingInfoResponseDTO info : ongoingFundingInfo) {
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
        if (link.isPresent()){
            isLinked = link.get().getAutoFundingStatus();
        }
        List<FundingProductDTO> products = fundingProductsRepository.findByFundingId(fundingId);

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

    public DirectFundingInfoResponseDTO getDonationInfo(Long fundingId, Long memberId) {
        FundingInfoDTO fundingInfo = fundingRepository.getDonationInfo(fundingId);
        Optional<Paymoney> optionalPaymoney = paymoneyRepository.findById(memberId);
        if (optionalPaymoney.isEmpty()) {
            return null;
        }
        Paymoney paymoneyInfo = optionalPaymoney.get();

        return DirectFundingInfoResponseDTO.builder()
            .fundingId(fundingInfo.getFundingId())
            .amount(fundingInfo.getAmount())
            .facilityName(fundingInfo.getFacilityName())
            .paymoneyAmount(paymoneyInfo.getPaymoneyAmount())
            .build();

    }
}
