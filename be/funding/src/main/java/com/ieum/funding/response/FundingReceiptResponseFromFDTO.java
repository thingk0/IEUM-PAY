package com.ieum.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundingReceiptResponseDTO {
    private String facilityName;
    private String facilityTitle;
    private String productName;
}
