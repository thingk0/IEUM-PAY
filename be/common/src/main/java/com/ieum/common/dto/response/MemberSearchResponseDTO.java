package com.ieum.common.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSearchResponseDTO {
    String name;
    String phoneNumber;
}
