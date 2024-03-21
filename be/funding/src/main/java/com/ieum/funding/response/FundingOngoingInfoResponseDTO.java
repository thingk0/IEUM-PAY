package com.ieum.funding.response;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingOngoingInfoResponseDTO {
    Long fundingId;
    String facilityName;
    String fundingTitle;
    Date fundingOpenDate;
    Date fundingFinishDate;
    String facilityImage;
    int fundingPeopleCnt;
    int goalAmount;
    int currentAmount;
}

