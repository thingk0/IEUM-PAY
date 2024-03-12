package com.ieum.common.response;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingCompleteInfoResponseDTO {
    Long fundingId;
    String facilityName;
    String fundingTitle;
    Date fundingOpenDate;
    Date fundingFinishDate;
    String facilityImage;
    int fundingPeopleCnt;
    int goalAmount;
}
