package com.ieum.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class DirectFundingInfoResponseDTO {
    private Long fundingId;
    private Integer amount;
    private String facilityName;
    private Integer paymoneyAmount;
}
