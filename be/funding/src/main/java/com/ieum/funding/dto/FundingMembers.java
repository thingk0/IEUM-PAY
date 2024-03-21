package com.ieum.funding.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

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
}
