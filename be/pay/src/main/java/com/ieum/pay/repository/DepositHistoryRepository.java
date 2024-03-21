package com.ieum.pay.repository;

import com.ieum.pay.domain.DepositHistories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositHistoryRepository extends JpaRepository<DepositHistories,Long> {
}
