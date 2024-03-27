package com.ieum.common.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paymoney {
    @Id
    @Column
    private Long memberId;

    @Column(nullable = false, length = 6)
    private String paymentPassword;

    @Column(nullable = false)
    private int paymoneyAmount;

    @Column(nullable = false)
    private int donationTotalAmount;

    @Column(nullable = false)
    private int donationCount;
}
