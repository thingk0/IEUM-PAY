package com.ieum.common.request;

import lombok.Data;

@Data
public class PayRemittancePaymoneyRequestDTO {
    String phoneNumber;
    int amount;
}
