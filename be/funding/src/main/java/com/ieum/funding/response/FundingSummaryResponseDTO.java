package com.ieum.funding.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class FundingSummaryResponseDTO {
    private Long fundingId;
    private String facilityName;
    private String fundingTitle;
    private String fundingOpenDate;
    private String fundingFinishDate;
    private String facilityImage;
    private Long fundingPeopleCnt;  // COUNT의 결과는 Long 타입이 될 수 있습니다.
    private Integer goalAmount;
    private Integer currentAmount;

    @Override
    public String toString() {
        return "FundingOngoingInfoResponseDTO{" +
            "fundingId=" + fundingId +
            ", facilityName='" + facilityName + '\'' +
            ", fundingTitle='" + fundingTitle + '\'' +
            ", fundingOpenDate='" + fundingOpenDate + '\'' +
            ", fundingFinishDate='" + fundingFinishDate + '\'' +
            ", facilityImage='" + facilityImage + '\'' +
            ", goalAmount=" + goalAmount +
            ", currentAmount=" + currentAmount +
            '}';
    }
}
