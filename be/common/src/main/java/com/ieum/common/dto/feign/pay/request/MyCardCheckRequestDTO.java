package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyCardCheckRequestDTO {
    Long memberId;
    Long registeredCardId;
}
