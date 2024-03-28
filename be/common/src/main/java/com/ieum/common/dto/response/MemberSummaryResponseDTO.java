package com.ieum.common.dto.response;

import com.ieum.common.dto.FundingInfoDTO;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSummaryResponseDTO {
    String name;
    String nickname;
    String  gradeCode;
    String gradeName;
    Long totalDonationCnt;
    int totalDonationAmount;
    Long autoFundingId;
    String facilityName;
    String facilityImage;
    int fundingTotalAmount;
}
