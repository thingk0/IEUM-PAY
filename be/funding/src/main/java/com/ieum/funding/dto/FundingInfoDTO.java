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
public class FundingInfoDTO {
    private Long fundingId;
    private Integer amount;
    private String facilityName;
}
