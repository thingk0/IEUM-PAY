package com.ieum.common.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptResponseDTO {
    private Long fundingId;
    private String fundingTitle;
    private String facilityName;
    private LocalDateTime historyDate;
    private String name;
    private Integer donationAmount;
    private String fundingSummary;

}
