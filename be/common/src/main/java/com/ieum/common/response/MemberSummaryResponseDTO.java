package com.ieum.common.response;

import com.ieum.common.dto.FundingInfoDTO;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSummaryResponseDTO {
    String name;
    String nickname;
    int gradeCode;
    String gradeName;
    int totalDonationCnt;
    int totalDonationAmount;
    List<FundingInfoDTO> fundingInfoList;
    Long autoFundingId;
    String autoFundingTitle;
    String autoFundingImg;
    int autoFundingAmount;
}
