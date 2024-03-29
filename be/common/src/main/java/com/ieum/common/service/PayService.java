package com.ieum.common.service;

import com.ieum.common.dto.feign.pay.request.*;
import com.ieum.common.dto.feign.pay.response.*;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import com.ieum.common.feign.PayFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PayService {

    private final PayFeignClient payFeignClient;

    public CardOcrResponseDTO getOcr(MultipartFile img) {
        return payFeignClient.getCardOcrResult(img);
    }

    public Long cardRegister(Long memberId, String cardNumber, String cardNickname) {
        return payFeignClient.isCardNumberValid(CardRegisterRequestDTO.builder()
                .memberId(memberId)
                .cardNumber(cardNumber)
                .cardNickname(cardNickname)
                .build());
    }

    public boolean updateCard(Long memberId ,Long cardId) {
        return payFeignClient.deleteCard( CardValidRequestDTO.builder()
                .memberId(memberId)
                .registeredCardId(cardId)
                .build());
    }

    public MainSummaryResponseDTO getMainPageInfo(Long memberId) {
        return payFeignClient.getMainPageInfo(memberId);
    }

    public boolean createPaymoney(Long memberId, String payPassword){
        return payFeignClient.createPayMoneyRegister(RegisterRequestDTO.builder()
                        .memberId(memberId)
                        .paymentPassword(payPassword)
                        .build());
    }

    public boolean updatePayPassword(Long memberId, String paymentPassword){
        return payFeignClient.updatePayPassword(MemberPayPasswordRequestDTO.builder()
                .memberId(memberId)
                .paymentPassword(paymentPassword)
                .build());
    }

    public FundingDonationResultResponseDTO getDonationHistory(Long historyId){
        return payFeignClient.getDonationHistory(historyId);
    }

    public Long directDonation(Long memberId, Long fundingId, int donationAmount, Long cardId){
        return payFeignClient.directDonation(FundingDonationRequestDTO.builder()
                .memberId(memberId)
                .fundingId(fundingId)
                .donationAmount(donationAmount)
                .cardId(cardId)
                .build());
    }

    public List<HistoryResponseDTO> getHistoryList(Long memberId){
        return payFeignClient.history(memberId);
    }

    public int HowManyCharge(Long memberId, int tradeAmount){
        return payFeignClient.chargeMoney(PaymentChargeMoney.builder()
                .memberId(memberId)
                .paymentMoney(tradeAmount)
                .build());
    }

    public Long payment(Long memberId,Long storeId, Long fundingId, Long cardId, int amount, int donationAmount,
                        int chargeAmount){
        return payFeignClient.payment(PaymentRequestDTO.builder()
                .memberId(memberId)
                .storeId(storeId)
                .fundingId(fundingId)
                .cardId(cardId)
                .amount(amount)
                .donationAmount(donationAmount)
                .chargeAmount(chargeAmount)
                .build());
    }

    public String getStoreName(Long storeId){
        return payFeignClient.getStoreName(storeId);
    }

    public PaymentHistoryPayResponseDTO getPaymentHistory(Long memberId, Long historyId){
        return payFeignClient.getPaymentHistory(historyId);
    }

    public int nowMyPaymoney(Long memberId){
        return payFeignClient.myPaymoney(memberId);
    }

    public Long remmitancePaymoney(Long member, String memberName, Long receiverId,
                                   String receiverName, int amount, Long cardId){
        return payFeignClient.remittancePaymoney(RemittanceRequestDTO.builder()
                .senderId(member)
                .senderName(memberName)
                .receiverId(receiverId)
                .receiverName(receiverName)
                .amount(amount)
                .cardId(cardId)
                .build());
    }

    public Long remmitanceAccount(Long memberId, String memberName, String receiverName,
                                  int amount, Long cardId){
        return payFeignClient.remittanceAccount(RemittanceAccountRequestDTO.builder()
                .senderId(memberId)
                .senderName(memberName)
                .receiverName(receiverName)
                .amount(amount)
                .cardId(cardId)
                .build());
    }

    public DonationReceiptResponseDTO getDonationReceiptInfo(Long memberId, Long historyId){
        return payFeignClient.donationReceipt(memberId,historyId);
    }

    public boolean checkMyCard(Long memberId,Long registeredCardId){
        return payFeignClient.isMine(MyCardCheckRequestDTO.builder()
                .memberId(memberId)
                .registeredCardId(registeredCardId)
                .build());
    }
    public RemittanceHistoryResponseDTO getRemittanceHistory(Long memberId, Long historyId){
        return payFeignClient.getRemittanceHistory(memberId,historyId);
    }

    public String getCardName(Long registeredCardId){
        return payFeignClient.getCardName(registeredCardId);
    }

    public Object getMainSummary(Long memberId) {
        return payFeignClient.getMainPageInfo(memberId);
    }
}
