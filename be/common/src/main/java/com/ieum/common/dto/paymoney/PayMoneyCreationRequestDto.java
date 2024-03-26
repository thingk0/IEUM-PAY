package com.ieum.common.dto.paymoney;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayMoneyCreationRequestDto {

    private Long memberId;
    private String payPasswd;
}
