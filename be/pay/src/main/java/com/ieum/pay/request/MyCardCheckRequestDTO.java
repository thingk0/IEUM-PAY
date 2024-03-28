package com.ieum.pay.request;

import lombok.Data;

@Data
public class MyCardCheckRequestDTO {
    Long memberId;
    Long registeredCardId;
}
