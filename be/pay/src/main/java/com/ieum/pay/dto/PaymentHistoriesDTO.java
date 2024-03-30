package com.ieum.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


public interface PaymentHistoriesDTO {
    Long getPayhisid();
    Long getHisid();
     Integer getAmount();
     Boolean getPayment();
     Long getSId();
     String getSname();

}
