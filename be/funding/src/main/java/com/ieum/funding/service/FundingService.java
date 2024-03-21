package com.ieum.funding.service;

import com.ieum.funding.repository.FundingProductsRepository;
import com.ieum.funding.repository.FundingRepository;
import com.ieum.funding.repository.SponsorProductsRepository;
import com.ieum.funding.repository.SponsorRepository;
import com.ieum.funding.response.FundingOngoingInfoResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FundingService {

    private final FundingRepository fundingRepository;
    private final FundingProductsRepository fundingProductsRepository;
    private final SponsorRepository sponsorRepository;
    private final SponsorProductsRepository sponsorProductsRepository;

    public List<FundingOngoingInfoResponseDTO> getFundingOngoingList() {
        return null;
    }
}
