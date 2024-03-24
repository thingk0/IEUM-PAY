package com.ieum.funding.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paymoney {
    @Id
    private Long memberId;
    private String paymentPassword;
    private Integer paymoneyAmount;
    private Integer donationTotalAmount;
    private Integer donationCount;
}
