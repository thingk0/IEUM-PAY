package com.ieum.pay.repository;

import com.ieum.pay.domain.Paymoney;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymoneyRepository extends JpaRepository<Paymoney,Long> {
    Paymoney findByMemberId(Long memberId);
}
