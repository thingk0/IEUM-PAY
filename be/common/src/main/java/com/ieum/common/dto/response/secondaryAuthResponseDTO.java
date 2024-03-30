package com.ieum.common.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class secondaryAuthResponseDTO {
    private Boolean auth;
    private String authenticationKey;
}
