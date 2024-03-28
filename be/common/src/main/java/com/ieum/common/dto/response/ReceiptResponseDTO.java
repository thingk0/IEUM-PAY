package com.ieum.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptResponseDTO {
    private String facilityName;
    private String fundingTitle;
    private String productName;
    private Integer paymoney;
}
