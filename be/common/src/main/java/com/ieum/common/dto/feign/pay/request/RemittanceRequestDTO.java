package com.ieum.common.dto.feign.pay.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemittanceRequestDTO {
    Long senderId;
    String senderName;
    Long receiverId;
    String receiverName;
    int amount;
    Long cardId;
}
