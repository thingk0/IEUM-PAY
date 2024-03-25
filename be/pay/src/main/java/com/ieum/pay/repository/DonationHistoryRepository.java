package com.ieum.pay.repository;

import com.ieum.pay.domain.DonationHistories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationHistoryRepository extends JpaRepository<DonationHistories, Long> {
    List<DonationHistories> findByMemberId(Long memberId);
}
