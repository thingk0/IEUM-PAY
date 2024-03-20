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
public class ChargeHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chargeHistoryId;

    private Long registeredCardId;

    private Long historyId;

    private Long memberId;

    private Integer chargeAmount;
}
