package com.ieum.pay.repository;

import com.ieum.pay.domain.Histories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<Histories, Long> {
    List<Histories> findByMemberIdOrderByHistoryDateDesc(Long memberId);

    Histories findByHistoryId(Long historyId);
}
