package com.ieum.common.dto.request;

import lombok.Data;

@Data
public class PayRemittancePaymoneyRequestDTO {
    String authenticationKey;
    String phoneNumber;
    int amount;
}
