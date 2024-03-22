package com.ieum.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DonationHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationHistoryId;

    private Long historyId;

    private Long memberId;

    private Long fundingId;

    private Integer donationAmount;

}
