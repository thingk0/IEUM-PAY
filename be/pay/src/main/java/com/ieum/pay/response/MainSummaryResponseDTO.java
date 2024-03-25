package com.ieum.pay.response;

import com.ieum.pay.dto.CardDTO;
import lombok.Getter;

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
