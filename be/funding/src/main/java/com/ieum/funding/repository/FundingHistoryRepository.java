package com.ieum.funding.repository;

import com.ieum.funding.dto.FundingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingHistoryRepository extends JpaRepository<FundingHistory, Long> {

}
