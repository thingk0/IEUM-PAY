package com.ieum.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepositHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionHistoryId;

    private Long historyId;

    private Long memberId;

    private Integer transactionAmount;

    private String depositBrief;

}
