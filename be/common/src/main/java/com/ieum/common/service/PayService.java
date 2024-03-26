package com.ieum.common.service;

import com.ieum.common.dto.etc.MainPageResponseDto;
import com.ieum.common.dto.request.CardRegisterRequestDTO;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import com.ieum.common.feign.PayFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PayService {

    private final PayFeignClient payFeignClient;

    public CardOcrResponseDTO getOcr(MultipartFile img) {
        return payFeignClient.getCardOcrResult(img);
    }

    public Long cardRegister(CardRegisterRequestDTO requesterDTO) {
        return payFeignClient.isCardNumberValid(requesterDTO);
    }

    public void updateCard(Long id) {
        payFeignClient.deleteCard(id);
    }

    public MainPageResponseDto getMainPageInfo(Long memberId) {
        return payFeignClient.getMainPageInfo(memberId);
    }
}
