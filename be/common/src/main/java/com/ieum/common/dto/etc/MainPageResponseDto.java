package com.ieum.common.dto.etc;

import com.ieum.common.dto.card.CardResponseDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainPageResponseDto {

    private List<CardResponseDto> cardList;
    private int paymentAmount;
    private int totalDonation;

}
