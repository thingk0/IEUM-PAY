package com.ieum.pay.repository;

import com.ieum.pay.domain.ChargeHistories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargeHistoryRepository extends JpaRepository<ChargeHistories,Long> {
    List<ChargeHistories> findByMemberId(Long memberId);
}
