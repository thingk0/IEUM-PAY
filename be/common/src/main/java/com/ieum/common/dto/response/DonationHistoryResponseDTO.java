package com.ieum.common.dto.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DonationHistoryResponseDTO {

    private Long fundingId;
    private String fundingTitle;
    private String facilityName;
    private Date historyDate;
    private String nickname;
    private int donationAmount;
    private String fundingSummary;

}
