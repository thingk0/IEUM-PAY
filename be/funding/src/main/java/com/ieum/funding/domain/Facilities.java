package com.ieum.funding.domain;

import javax.persistence.Column;
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
public class Facilities {
    @Id
    private Long facilityId;
    private String facilityTypeCode;
    private String facilityName;
    private String facilityAddress;
    private String facilityPhoneNumber;
    private String facilityRepresentativeName;
    private String facilityRepresentativePhoneNumber;
    private Integer facilityCapacity;
    @Column(length = 500)
    private String facilityImage;
}
