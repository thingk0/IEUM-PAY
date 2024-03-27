package com.ieum.common.dto.feign.pay.response;

import com.ieum.common.dto.feign.pay.dto.HistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponseDTO {
    Long historyId;
    LocalDateTime historyDate;
    String type;
    String title;
    int amount;
    List<HistoryDTO> detail;

    public HistoryResponseDTO(Long historyId, String historyType, Integer totalAmount, LocalDateTime historyDate) {
        this.historyId = historyId;
        this.historyDate = historyDate;
        this.type = historyType;
        this.amount = totalAmount;
        this.detail = new ArrayList<>();
    }
}
