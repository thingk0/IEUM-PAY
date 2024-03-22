package com.ieum.pay.repository;

import com.ieum.pay.domain.WithdrawalHistories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalHistoryRepository extends JpaRepository<WithdrawalHistories, Long> {
    List<WithdrawalHistories> findByMemberId(Long memberId);
}
