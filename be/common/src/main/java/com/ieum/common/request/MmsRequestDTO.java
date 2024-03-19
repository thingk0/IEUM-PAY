package com.ieum.common.request;

import lombok.Data;

@Data
public class MmsRequestDTO {
    String phoneNumber;
    String authCode;
}
