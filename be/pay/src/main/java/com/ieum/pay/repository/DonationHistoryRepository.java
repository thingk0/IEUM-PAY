package com.ieum.pay.repository;

import com.ieum.pay.domain.DonationHistories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationHistoryRepository extends JpaRepository<DonationHistories, Long> {
}
