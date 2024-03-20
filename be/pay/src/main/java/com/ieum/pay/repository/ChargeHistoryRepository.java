package com.ieum.pay.repository;

import com.ieum.pay.domain.ChargeHistories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeHistoryRepository extends JpaRepository<ChargeHistories,Long> {
}
