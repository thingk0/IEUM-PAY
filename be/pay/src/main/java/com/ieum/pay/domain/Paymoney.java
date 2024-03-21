package com.ieum.pay.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paymoney {
    @Id
    @Column
    private Long memberId;

    @Column(nullable = false, length = 6)
    private String paymentPassword;

    @Setter
    @Column(nullable = false)
    private int paymoneyAmount;

    @Column(nullable = false)
    private int donationTotalAmount;

    @Column(nullable = false)
    private int donationCount;
}
