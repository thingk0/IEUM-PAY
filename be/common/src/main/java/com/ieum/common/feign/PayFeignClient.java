package com.ieum.common.feign;

import com.ieum.common.dto.etc.MainPageResponseDto;
import com.ieum.common.dto.paymoney.PayMoneyCreationRequestDto;
import com.ieum.common.dto.feign.pay.request.*;
import com.ieum.common.dto.feign.pay.response.*;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * -1 : 카드 번호 유효성 불가 번호
     * -2 : 잘못된 카드번호 ( 16자리가 아님, 숫자가 아님)
     * 양수 : 둥록된 카드 번호 id (registeredCardId)
     */
    @PostMapping("/card/valid")
    Long isCardNumberValid(@RequestBody CardRegisterRequestDTO cardNumber);

    /**
     * 카드 삭 제 기능을 제공하는 메서드
     *
     *  삭제할 카드 ID
     */
    @PutMapping(value = "/card/delete")
    boolean deleteCard(@RequestBody CardValidRequestDTO requestDTO);

    /**
     * 회원의 결제 정보를 생성합니다.
     * <p>
     * 이 메서드는 회원의 결제 정보를 생성하기 위해 {@link PayMoneyCreationRequestDto} 객체를 요청 본문으로 받습니다. 결제 정보가 성공적으로 생성되면 true를 반환하고, 그렇지 않으면 false를 반환합니다. 이 메서드는
     * {@code /api/member/regist} 엔드포인트에 대한 POST 요청을 처리하기 위해 사용됩니다.
     *
     * @param requestDTO 결제 정보 생성을 위한 요청 데이터를 담고 있는 {@link PayMoneyCreationRequestDto} 객체. 이 객체는 요청 본문(@RequestBody)을 통해 전달되며, 결제에 필요한 정보를 포함해야 합니다.
     * @return 결제 정보 생성 성공 여부를 나타내는 boolean 값. 성공적으로 생성된 경우 true, 그렇지 않은 경우 false 반환.
     */
    @PostMapping(value = "/member/register")
    boolean createPayMoneyRegister(@RequestBody RegisterRequestDTO requestDTO);


    /**
     * 메인화면 조회 메서드
     */
    @GetMapping("/main/summary/{memberId}")
    MainPageResponseDto getMainPageInfo(@PathVariable("memberId") Long memberId);

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
     * 직접 기부 완료 정보 요청 메서드
     */
    @GetMapping("/funding/donation/result/{historyId}")
    FundingDonationResultResponseDTO getDonationHistory(@PathVariable("historyId") Long historyId);

    /**
     * 직접기부 메서드
     */
    @PostMapping("/funding/donation")
    Long directDonation(@RequestBody FundingDonationRequestDTO requestDTO);

    /**
     * 사용 내역 리스트 요청 메서드
     */
    @GetMapping("/history/{memberId}")
    List<HistoryResponseDTO> history(@PathVariable("memberId") Long memberId);

    /**
     * 메인화면 조회 메서드
     */
    @GetMapping("/main/summary/{memberId}")
    MainSummaryResponseDTO getMainSummary(@PathVariable("memberId") Long memberId);

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
    String getStoreName(@PathVariable("storeId") Long storeId);

    /**
     * 결제 완료 정보 요청 메서드
     */
    @GetMapping("/payment/{historyId}")
    PaymentHistoryResponseDTO getPaymentHistory(@PathVariable("historyId") Long historyId);

    /**
     * 현재 페이머니 반환 메서드
     */
    @GetMapping("/paymoney/{memberId}")
    int myPaymoney(@PathVariable("memberId") Long memberId);

    /**
     * 페이머니 송금하는 메서드
     */
    @PostMapping("/remittance")
    Long remittancePaymoney(@RequestBody RemittanceRequestDTO requestDTO);

    @PostMapping("/remittance/account")
    public Long remittanceAccount(@RequestBody RemittanceAccountRequestDTO requestDTO);

    @GetMapping("/donation/{memberId}/{historyId}")
    public DonationReceiptResponseDTO donationReceipt (@PathVariable("memberId") Long memberId, @PathVariable("historyId") Long historyId);

    String getPaymentPassword(Long memberId);
}