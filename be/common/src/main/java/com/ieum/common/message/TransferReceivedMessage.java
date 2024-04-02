package com.ieum.common.message;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferReceivedMessage {

    private int amount;
    private Long senderId;
    private Long transferId;
    private Long receiverId;
}
