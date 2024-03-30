package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemittanceAccountRequestDTO {
    Long senderId;
    String senderName;
    String receiverName;
    int amount;
    Long cardId;
}
