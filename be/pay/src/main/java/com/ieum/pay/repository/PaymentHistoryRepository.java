package com.ieum.pay.repository;

import com.ieum.pay.domain.PaymentHistories;
import com.ieum.pay.dto.PaymentHistoriesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistories, Long> {
        @Query(value = "SELECT " +
                "p.payment_history_id as payhisid, p.history_id as hisid" +
                ", p.payment_amount as amount, p.payment as payment" +
                " , p.store_id as sid , s.store_name as sname" +
                " FROM payment_histories p " +
                "LEFT JOIN stores s on p.store_id = s.store_id where p.member_id = :id ", nativeQuery = true)
    List<PaymentHistoriesDTO> findByMemberId(Long id);

    PaymentHistories findByHistoryId(Long historyId);
}