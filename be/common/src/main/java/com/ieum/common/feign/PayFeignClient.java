package com.ieum.common.feign;

import com.ieum.common.dto.request.CardRegisterRequestDTO;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "pay", url = "${gateway.pay}")
public interface PayFeignClient {

    /**
     * 카드 OCR 기능을 제공하는 메서드
     *
     * @param img 카드 이미지 파일
     * @return 카드 OCR 결과
     */
    @PostMapping(value = "/card/ocr", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CardOcrResponseDTO getCardOcrResult(@RequestBody MultipartFile img);

    /**
     * 카드 번호 유효성 검사 기능을 제공하는 메서드
     *
     * @param cardNumber 카드 번호 정보
     * @return 카드 번호 유효성 검사 결과 - 카드 ID
     */
    @PostMapping(value = "/card/valid")
    Long isCardNumberValid(@RequestBody CardRegisterRequestDTO cardNumber);

    /**
     * 카드 삭 제 기능을 제공하는 메서드
     *
     * @param id 삭제할 카드 ID
     */
    @PutMapping(value = "/card/delete")
    void deleteCard(Long id);

    /**
     * 페이머니 생성 기능을 제공하는 메서드
     *
     * @param memberId 페이머니를 생성할 회원 ID
     * @return 페이머니 생성 성공 여부
     */
    @PostMapping(value = "/pay-money")
    boolean createPayMoney(Long memberId);

}