package com.ieum.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationDirectlyResponseDTO {
    private Long fundingId;
    private String facilityName;
    private String facilityTitle;
    private String facilityImage;
    private Integer fundingAmount;
}
