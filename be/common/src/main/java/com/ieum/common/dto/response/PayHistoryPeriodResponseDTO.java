package com.ieum.common.dto.response;

import com.ieum.common.dto.HistoryDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PayHistoryPeriodResponseDTO {
    Long historyId;
    String type;
    String title;
    int amount;
    List<HistoryDTO> detail;
}
