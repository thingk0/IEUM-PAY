package com.ieum.common.dto.feign.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FundingProductDTO {
    private Integer fundingProductQuantity;
    private Integer fundingProductPrice;
    private String productName;
    private String productImage;
    private Integer price;
    private String sponsorName;
}
