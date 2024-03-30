package com.ieum.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DirectlyDonationInfoResponseDTO {
    private Long fundingId;
    private Integer amount;
    private String facilityName;
    private int paymoneyAmount;

}
