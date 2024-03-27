package com.ieum.common.dto.feign.pay.dto;

import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDTO {
    String type;
    String name;
    int price;
}
