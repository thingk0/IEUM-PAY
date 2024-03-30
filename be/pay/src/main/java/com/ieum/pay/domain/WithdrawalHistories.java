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
public class WithdrawalHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalHistoryId;

    private Long historyId;

    private Long memberId;

    private Integer transactionAmount;

    private String withdrawalBrief;
}
