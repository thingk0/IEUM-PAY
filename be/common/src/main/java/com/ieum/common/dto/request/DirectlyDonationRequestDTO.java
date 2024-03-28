package com.ieum.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectlyDonationRequestDTO {
    private Long fundingId;
    private Integer amount;
    private Integer paymoneyAmount;
    private String authenticationKey;

}
