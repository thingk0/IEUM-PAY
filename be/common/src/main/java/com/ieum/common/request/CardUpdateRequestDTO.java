package com.ieum.common.request;

import lombok.Data;

@Data
public class CardUpdateRequestDTO {
    Long registeredCardId;
    String authenticationKey;
}
