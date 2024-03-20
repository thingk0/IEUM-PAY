package com.ieum.pay.repository;

import com.ieum.pay.domain.WithdrawalHistories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalHistoryRepository extends JpaRepository<WithdrawalHistories, Long> {
}
