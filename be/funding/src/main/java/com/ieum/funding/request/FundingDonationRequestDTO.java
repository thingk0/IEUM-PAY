package com.ieum.funding.request;

import lombok.Data;

@Data
public class FundingDonationRequestDTO {
    private Long fundingId;
    private Long memberId;
    private Long amount;
    // 추가적인 데이터 필요
    // ex 금액
}
