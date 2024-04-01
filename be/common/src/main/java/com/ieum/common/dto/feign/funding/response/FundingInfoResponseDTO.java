package com.ieum.common.dto.feign.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundingInfoResponseDTO {
    private Long fundingId;
    private Integer amount;
    private String facilityName;
}
