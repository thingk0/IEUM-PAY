package com.ieum.common.dto.feign.pay.request;

import lombok.Data;

@Data
public class RemittanceRequestDTO {
    Long senderId;
    String senderName;
    Long receiverId;
    String receiverName;
    int amount;
    Long cardId;
}
