package com.ieum.common.response;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DonationHistoryResponseDTO {
    Long fundingId;
    String fundingTitle;
    String facilityName;
    Date historyDate;
    String nickname;
    int donationAmount;
    String fundingSummary;
}
