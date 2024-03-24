package com.ieum.common.dto.request;

import lombok.Data;

@Data
public class PayRemittancePaymoneyRequestDTO {
    String phoneNumber;
    int amount;
}
