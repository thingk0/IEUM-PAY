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
public class FundingMembers {

    @Id
    private Long fundingMemberId;
    private Long memberId;
    private Long fundingId;
    private Integer fundingTotalAmount;
    private Boolean autoFundingStatus;
    private String nickname;
}
