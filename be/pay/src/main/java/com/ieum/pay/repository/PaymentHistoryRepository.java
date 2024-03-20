package com.ieum.pay.repository;

import com.ieum.pay.domain.PaymentHistories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistories, Long> {
}
