package com.ieum.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private String fundingTitle;
    private String facilityName;
    private String facilityImage;
    private Integer fundingAmount;
}


