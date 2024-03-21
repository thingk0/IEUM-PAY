package com.ieum.funding.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder.In;
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
public class Receipts {
    @Id
    private Long receiptId;
    private Long fundingPeopleId;
    @Setter
    private Integer fundingTotalAmount;
}
