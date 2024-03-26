package com.ieum.pay.request;

import lombok.Data;

@Data
public class RemittanceAccountRequestDTO {
    Long senderId;
    String senderName;
    String receiverName;
    int amount;
    Long cardId;
}
