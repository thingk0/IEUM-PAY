package com.ieum.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentHistoryId;

    private Long historyId;

    private Long memberId;

    private Integer paymentAmount;

    private Boolean payment;

    private Long storeId;
}
