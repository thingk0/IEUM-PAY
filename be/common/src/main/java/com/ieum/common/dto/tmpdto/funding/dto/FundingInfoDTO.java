package com.ieum.common.dto.tmpdto.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class FundingInfoDTO {
    private Long fundingId;
    private Integer amount;
    private String facilityName;
}
