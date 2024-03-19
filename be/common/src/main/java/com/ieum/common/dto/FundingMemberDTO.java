package com.ieum.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingMemberDTO {
    String nickname;
    int amount;
}
