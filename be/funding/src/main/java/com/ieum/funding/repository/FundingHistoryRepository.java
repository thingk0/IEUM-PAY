package com.ieum.funding.repository;

import com.ieum.funding.domain.FundingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingHistoryRepository extends JpaRepository<FundingHistory, Long> {

}
