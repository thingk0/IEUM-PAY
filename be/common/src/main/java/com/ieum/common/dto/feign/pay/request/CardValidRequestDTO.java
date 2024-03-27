package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardValidRequestDTO {
    Long memberId;
    Long registeredCardId;
}
