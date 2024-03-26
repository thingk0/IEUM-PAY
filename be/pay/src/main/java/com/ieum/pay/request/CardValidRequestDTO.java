package com.ieum.pay.request;

import lombok.Data;

@Data
public class CardValidRequestDTO {
    Long memberId;
    Long registeredCardId;
}
