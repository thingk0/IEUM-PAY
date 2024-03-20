package com.ieum.common.service;

import com.ieum.common.feign.PayFeignClient;
import com.ieum.common.request.CardRegisterRequestDTO;
import com.ieum.common.response.CardOcrResponseDTO;
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
        return payFeignClient.cardOcr(img);
    }

    public Long cardRegister(CardRegisterRequestDTO requesterDTO) {
        return payFeignClient.cardValid(requesterDTO);
    }

    public void updateCard(Long id) {
        payFeignClient.cardDelete(id);
    }
}
