package com.ieum.pay.repository;

import com.ieum.pay.domain.DepositHistories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositHistoryRepository extends JpaRepository<DepositHistories,Long> {
    List<DepositHistories> findByMemberId(Long memberId);
}
