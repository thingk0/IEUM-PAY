package com.ieum.common.dto.feign.pay.response;

import lombok.Getter;
import com.ieum.common.dto.feign.pay.dto.CardDTO;
import java.util.ArrayList;
import java.util.List;

@Getter
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
