package com.ieum.funding.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SponsorProducts {
    @Id
    private Long sponsorProductId;

    private String sponsorTypeCode;
    private String productName;
    private Integer price;
    private String productImage;
}
