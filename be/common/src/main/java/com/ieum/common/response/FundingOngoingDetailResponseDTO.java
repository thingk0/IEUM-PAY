package com.ieum.common.response;

import com.ieum.common.dto.FundingMemberDTO;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.bson.Document;

@Getter
@Builder
public class FundingOngoingDetailResponseDTO {
    String facilityName;
    String facilityAddress;
    String facilityPhoneNumber;
    String facilityRepresentativeName;
    String facilityRepresentativePhoneNumber;
    int facilityCapacity;
    String facilityImage;
    String fundingOpenDate;
    int fundingPeopleCnt;
    String fundingTitle;
    int goalAmount;
    int currentAmount;
    List<FundingMemberDTO> fundingMemberList;
    Document content;

}
