package com.ieum.common.service;

import com.ieum.common.domain.Members;
import com.ieum.common.dto.feign.funding.request.AutoDonationRequestDTO;
import com.ieum.common.dto.feign.funding.request.FundingDonationRequestDTO;
import com.ieum.common.dto.feign.funding.response.AutoFundingResultResponseDTO;
import com.ieum.common.dto.feign.funding.response.FundingInfoResponseDTO;
import com.ieum.common.dto.feign.pay.response.PaymentHistoryPayResponseDTO;
import com.ieum.common.dto.request.PaymentRequestDTO;
import com.ieum.common.dto.response.PaymentHistoryResponseDTO;
import com.ieum.common.dto.response.PaymentInfoResponseDTO;
import com.ieum.common.dto.response.PaymentResponseDTO;
import com.ieum.common.feign.FundingFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PayService payService;
    private final FundingFeignClient fundingFeignClient;
    private final MemberService memberService;


    public PaymentResponseDTO processPayment(Long memberId, PaymentRequestDTO dto) {
        //연동 확인
        AutoFundingResultResponseDTO funding = fundingFeignClient.donationAuto(
            AutoDonationRequestDTO.builder()
                        .memberId(memberId)
                        .amount(dto.getDonationMoney())
                .build());
        Long cardId = memberService.findMemberById(memberId).getPaycardId();
        Long historyId = payService.payment(memberId,dto.getStoreId(),funding.getFundingId(),cardId, dto.getPrice(), funding.getAmount(), dto.getChargeAmount());
        return PaymentResponseDTO.builder()
                .historyId(historyId)
                .build();
    }

    public void verifyPaymentPassword() {

    }

    public boolean updatePaymentPassword(Long memberId, String pw) {
        return payService.updatePayPassword(memberId,pw);
    }

    public PaymentHistoryResponseDTO getPaymentHistory(Long memberId, Long historyId) {
        PaymentHistoryPayResponseDTO pay = payService.getPaymentHistory(memberId,historyId);
        String facilityName = "";
        if(pay.getFundingId() > 0L)
            facilityName = fundingFeignClient.getPaymentResult(pay.getFundingId()).getFacilityName();

        // funding 유무 판단
        return PaymentHistoryResponseDTO.builder()
                .storeName(pay.getStoreName())
                .paymentAmount(pay.getPaymentAmount())
                .donationAmount(pay.getDonationAmount())
                .facilityName(facilityName)
                .build();
    }

    public PaymentInfoResponseDTO getPaymentInfo(Long memberId, Long storeId, int price) {
        Members member = memberService.findMemberById(memberId);
        FundingInfoResponseDTO funding = fundingFeignClient.getAutoFundingInfo(memberId);
        //기부 가능한 총액
        String storeName = payService.getStoreName(storeId);
        String cardName = payService.getCardName(member.getPaycardId());
        int nowPaymoney = payService.nowMyPaymoney(memberId);
        int chargeMoney = 0;
        if(price > nowPaymoney){
            chargeMoney = (price - nowPaymoney) / 10000 * 10000;
            if(price - nowPaymoney > chargeMoney)
                chargeMoney += 10000;
        }

        int donationMoney = 0;
        String facilityName = "";
        if(funding != null){
            donationMoney = (chargeMoney + nowPaymoney - price) % 1000;

            if(donationMoney > funding.getAmount()){
                donationMoney = funding.getAmount();
            }
            facilityName = funding.getFacilityName();
        }
        return PaymentInfoResponseDTO.builder()
                .storeId(storeId)
                .price(price)
                .cardNickname(cardName)
                .storeName(storeName)
                .paymoneyAmount(nowPaymoney)
                .chargeAmount(chargeMoney)
                .donationMoney(donationMoney)
                .facilityName(facilityName)
                .build();
    }
}