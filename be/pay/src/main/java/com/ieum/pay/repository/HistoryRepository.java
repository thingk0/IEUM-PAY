package com.ieum.pay.repository;

import com.ieum.pay.domain.Histories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<Histories, Long> {
}
