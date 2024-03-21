package com.ieum.funding.dto;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundingId;

    private Long facilityId;

    private Integer goalAmount;

    @Setter
    private Integer currentAmount;

    private String fundingTitle;

    private Timestamp fundingOpenDate;

    @Setter
    private Timestamp fundingFinishDate;

    private String fundingSummary;
}
