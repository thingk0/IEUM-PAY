package com.ieum.common.dto.feign.funding.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoDonationRequestDTO {
    private Long memberId;
    private Integer amount;
}
