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
public class Sponsors {

    @Id
    private String sponsorTypeCode;
    private String sponsorName;
    private String sponsorCategory;
}
