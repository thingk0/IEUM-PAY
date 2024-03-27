package com.ieum.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FundingDetailBaseDTO {
    private Long fundingId;
    private String facilityName;
    private String facilityAddress;
    private String facilityPhoneNumber;
    private String facilityRepresentativeName;
    private String facilityRepresentativePhoneNumber;
    private Integer facilityCapacity;
    private String facilityImage;
    private String fundingOpenDate;
    private String fundingFinishDate;
    private Long fundingPeopleCnt;
    private String fundingTitle;
    private Integer goalAmount;
    private Integer currentAmount;
    private String content;
}
