package com.ieum.common.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberExistResponseDTO {
    boolean exist;
}
