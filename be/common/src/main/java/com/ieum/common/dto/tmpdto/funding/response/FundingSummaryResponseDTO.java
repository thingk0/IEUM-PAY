package com.ieum.common.dto.tmpdto.funding.response;

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
    private Long fundingPeopleCnt;
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
