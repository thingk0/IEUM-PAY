package com.ieum.common.feign;

import com.ieum.common.dto.etc.MainPageResponseDto;
import com.ieum.common.dto.paymoney.PayMoneyCreationRequestDto;
import com.ieum.common.dto.request.CardRegisterRequestDTO;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 회원의 결제 정보를 생성합니다.
     * <p>
     * 이 메서드는 회원의 결제 정보를 생성하기 위해 {@link PayMoneyCreationRequestDto} 객체를 요청 본문으로 받습니다. 결제 정보가 성공적으로 생성되면 true를 반환하고, 그렇지 않으면 false를 반환합니다. 이 메서드는
     * {@code /api/member/regist} 엔드포인트에 대한 POST 요청을 처리하기 위해 사용됩니다.
     *
     * @param requestDto 결제 정보 생성을 위한 요청 데이터를 담고 있는 {@link PayMoneyCreationRequestDto} 객체. 이 객체는 요청 본문(@RequestBody)을 통해 전달되며, 결제에 필요한 정보를 포함해야 합니다.
     * @return 결제 정보 생성 성공 여부를 나타내는 boolean 값. 성공적으로 생성된 경우 true, 그렇지 않은 경우 false 반환.
     */
    @PostMapping(value = "/api/member/regist")
    boolean createPayMoney(@RequestBody PayMoneyCreationRequestDto requestDto);

    @GetMapping(value = "/api/main/summary")
    MainPageResponseDto getMainPageInfo(Long memberId);
}