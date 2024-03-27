package com.ieum.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundingProductDTO {
    private Integer fundingProductQuantity;
    private Integer fundingProductPrice;
    private String productName;
    private String productImage;
    private Integer price;
    private String sponsorName;
}
