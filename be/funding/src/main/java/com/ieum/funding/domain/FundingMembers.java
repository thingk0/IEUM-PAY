package com.ieum.funding.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class FundingMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundingMemberId;
    private Long memberId;
    private Long fundingId;
    private Integer fundingTotalAmount = 0;
    private Boolean autoFundingStatus = true;
    private String nickname;

    @Builder
    public FundingMembers(Long fundingId, Long memberId, String nickname) {
        this.fundingId = fundingId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.fundingTotalAmount = 0; // 기본값
        this.autoFundingStatus = true; // 기본값
    }
}
