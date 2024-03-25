package com.ieum.funding.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundingHistory {
    @Id
    private Long fundingHistoryId;
    private Long memberId;
    private Integer totalFundingParticipatedCount;
    private Integer completedFundingCount;
    private Integer ongoingFundingCount;
    private Long totalFundingAmount;
    private Long completedFundingAmount;
    private Long ongoingFundingAmount;
}
