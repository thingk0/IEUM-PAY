package com.ieum.common.dto.feign.pay.request;

import lombok.Data;

@Data
public class CardValidRequestDTO {
    Long memberId;
    Long registeredCardId;
}
