package com.ieum.common.dto.feign.pay.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.ieum.common.dto.feign.pay.dto.CardDTO;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainSummaryResponseDTO {
    int paymentAmount;
    int totalDonation;
    List<CardDTO> cardList;

    public MainSummaryResponseDTO(int paymentAmount, int totalDonation){
        this.paymentAmount = paymentAmount;
        this.totalDonation = totalDonation;
        cardList = new ArrayList<>();
    }
}
