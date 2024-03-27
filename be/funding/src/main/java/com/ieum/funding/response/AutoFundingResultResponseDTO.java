package com.ieum.funding.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoFundingResultResponseDTO {
    private Integer amount;
}
