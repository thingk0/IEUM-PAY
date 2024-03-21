package com.ieum.funding.repository;

import com.ieum.funding.dto.Funding;
import com.ieum.funding.response.FundingOngoingInfoResponseDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FundingRepository extends JpaRepository<Funding, Long> {

}
