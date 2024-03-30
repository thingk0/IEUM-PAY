package com.ieum.funding.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundingProducts {
    @Id
    private Long fundingProductId;

    private Long fundingId;
    private Long sponsorProductId;
    private Integer fundingProductQuantity;
    private Integer fundingProductPrice;
}
