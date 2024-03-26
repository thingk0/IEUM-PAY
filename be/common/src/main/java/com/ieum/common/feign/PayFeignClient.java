package com.ieum.common.feign;

import com.ieum.common.dto.feign.pay.request.*;
import com.ieum.common.dto.feign.pay.response.*;
import com.ieum.common.dto.request.CardRegisterRequestDTO;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @PostMapping("/card/valid")
    Long isCardNumberValid(@RequestBody CardRegisterRequestDTO cardNumber);

    /**
     * 카드 삭 제 기능을 제공하는 메서드
     *
     * @param id 삭제할 카드 ID
     */
    @PutMapping(value = "/card/delete")
    void deleteCard(Long id);
//    void deleteCard(@RequestBody CardValidRequestDTO requestDTO);

    /**
     * 페이머니 생성 기능을 제공하는 메서드
     *
     * @param memberId 페이머니를 생성할 회원 ID
     * @return 페이머니 생성 성공 여부
     */
    @PostMapping(value = "/pay-money")
    boolean createPayMoney(Long memberId);

    /**
     * 페이머니 생성 기능을 제공하는 메서드
     *
     * @RequestBody
     * @return 페이머니 생성 성공 여부
     */
    @PostMapping(value = "/member/register")
    boolean createPayMoneyRegister(@RequestBody RegisterRequestDTO requestDTO);

    /**
     * 간편결제비밀번호 변경 메서드
     */
    @PutMapping("/member/pay-pw")
    boolean updatePayPassword(@RequestBody MemberPayPasswordRequestDTO requestDTO);

    /**
     * 카드 등록 메서드
     *
     * @RequestBody
     * @return
     * -1 : 카드 번호 유효성 불가 번호
     * -2 : 잘못된 카드번호 ( 16자리가 아님, 숫자가 아님)
     * 양수 : 둥록된 카드 번호 id (registeredCardId)
     */
    @PostMapping("/card/valid")
    Long registerCard(@RequestBody CardRegisterRequestDTO requestDTO);

    /**
     * 카드 삭제 메서드
     */
    @PostMapping("/card/delete")
    public void isValidCardNumber(Long id);

    /**
     * 직접 기부 완료 정보 요청 메서드
     */
    @GetMapping("/funding/donation/result/{historyId}")
    FundingDonationResultResponseDTO getDonationHistory(@PathVariable Long historyId);

    /**
     * 직접기부 메서드
     */
    @PostMapping("/funding/donation")
    Long directDonation(@RequestBody FundingDonationRequestDTO requestDTO);

    /**
     * 사용 내역 리스트 요청 메서드
     */
    @GetMapping("/history/{memberId}")
    List<HistoryResponseDTO> history(@PathVariable Long memberId);

    /**
     * 메인화면 조회 메서드
     */
    @GetMapping("/main/summary/{memberId}")
    MainSummaryResponseDTO getMainSummary(@PathVariable Long memberId);

    /**
     * 결제하려는 금액에 맞게 충전 해야하는 돈 반환하는 메서드
     */
    @PostMapping("/payment/chargeMoney")
    int chargeMoney(@RequestBody PaymentChargeMoney requestDTO);

    /**
     * 결제 메서드
     */
    @PostMapping("/payment")
    Long payment(@RequestBody PaymentRequestDTO requestDTO);

    /**
     * 결제 정보 요청 메서드
     */
    @GetMapping("/payment/info/{storeId}")
    String getStoreName(@PathVariable Long storeId);

    /**
     * 결제 완료 정보 요청 메서드
     */
    @GetMapping("/payment/{historyId}")
    PaymentHistoryResponseDTO getPaymentHistory(@PathVariable Long historyId);

    /**
     * 현재 페이머니 반환 메서드
     */
    @GetMapping("/paymoney/{memberId}")
    int myPaymoney(@PathVariable long memberId);

    /**
     * 페이머니 송금하는 메서드
     */
    @PostMapping
    Long remittancePaymoney(@RequestBody RemittanceRequestDTO requestDTO);
}