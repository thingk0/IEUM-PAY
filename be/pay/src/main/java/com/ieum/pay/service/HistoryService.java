package com.ieum.pay.service;

import com.ieum.pay.domain.*;
import com.ieum.pay.dto.HistoryDTO;
import com.ieum.pay.dto.PaymentHistoriesDTO;
import com.ieum.pay.repository.*;
import com.ieum.pay.response.HistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final WithdrawalHistoryRepository withdrawalHistoryRepository;
    private final DepositHistoryRepository depositHistoryRepository;
    private final ChargeHistoryRepository chargeHistoryRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final DonationHistoryRepository donationHistoryRepository;

    public void sendMoney(Long senderId, String receiverName, int amount, int chargeMoney, Long cardId) {
        //사용자간 페이머니 송금시 출금순서
        //history
        Histories history = historySave(senderId,0,"출금");
        //withdrawal
        withdrawalHistorySave(history.getHistoryId(), senderId,amount,receiverName);

        if(chargeMoney > 0){
            chargeHistorySave(history.getHistoryId(), senderId,cardId,chargeMoney);
        }
    }

    public void receiveMoney(Long receiverId, String senderName, int amount) {
        //사용자간 페이머니 송금시 출금순서
        Histories history = historySave(receiverId,0,"입금");

        depositHistorySave(history.getHistoryId(), receiverId,amount,senderName);
    }

    public Long payment(Long memberId, Long storeId, Long fundingId, Long cardId, int chargeAmount, int amount, int donationAmount) {

        //history
        Histories result = historySave(memberId,0,"결제");// 0 revise check
        Long historyId = result.getHistoryId();
        //charge
        if(chargeAmount > 0){
            chargeHistorySave(historyId, memberId,cardId,chargeAmount);
        }
        //payment
        paymentHistorySave(historyId, memberId,storeId,amount,true);

        //donation
        if(donationAmount > 0){
            donationHistorySave(historyId,memberId,fundingId,donationAmount);
        }
        return historyId;

    }
    public Histories historySave(Long memberId, int totalAmount, String historyType){
        Histories history = Histories.builder()
                .memberId(memberId)
                .totalAmount(totalAmount)
                .historyType(historyType)
                .historyDate(LocalDateTime.now())
                .build();
        return historyRepository.save(history);
    }
    public void chargeHistorySave(Long historyId, Long memberId, Long cardId, int chargeMoney){
        ChargeHistories history = ChargeHistories.builder()
                .registeredCardId(cardId)
                .historyId(historyId)
                .memberId(memberId)
                .chargeAmount(chargeMoney)
                .build();
        chargeHistoryRepository.save(history);
    }
    public void withdrawalHistorySave(Long historyId, Long memberId, int amount, String brief){
        WithdrawalHistories history = WithdrawalHistories.builder()
                .historyId(historyId)
                .memberId(memberId)
                .transactionAmount(amount)
                .withdrawalBrief(brief)
                .build();
        withdrawalHistoryRepository.save(history);
    }
    public void depositHistorySave(Long historyId, Long memberId, int amount, String brief){
        DepositHistories history = DepositHistories.builder()
                .historyId(historyId)
                .memberId(memberId)
                .transactionAmount(amount)
                .depositBrief(brief)
                .build();
        depositHistoryRepository.save(history);
    }
    public void paymentHistorySave(Long historyId, Long memberId, Long storeId, int paymentAmount, boolean payment){
        PaymentHistories history = PaymentHistories.builder()
                .historyId(historyId)
                .memberId(memberId)
                .paymentAmount(paymentAmount)
                .payment(payment) // 결제 여부
                .storeId(storeId)
                .build();
        paymentHistoryRepository.save(history);
    }

    private void donationHistorySave(Long historyId, Long memberId, Long fundingId, int amount) {
        DonationHistories history = DonationHistories.builder()
                .historyId(historyId)
                .memberId(memberId)
                .fundingId(fundingId)
                .donationAmount(amount)
                .build();
        donationHistoryRepository.save(history);
    }

    public List<HistoryResponseDTO> historyList(Long memberId) {
        List<Histories> historyList = historyRepository.findByMemberIdOrderByHistoryDateDesc(memberId);

        List<WithdrawalHistories> withdrawalHistoryList = withdrawalHistoryRepository.findByMemberId(memberId);
        List<DepositHistories> depositHistoryRepositoryList = depositHistoryRepository.findByMemberId(memberId);
        List<ChargeHistories> chargeHistoryRepositoryList = chargeHistoryRepository.findByMemberId(memberId);
        List<PaymentHistoriesDTO> paymentHistoryRepositoryList = paymentHistoryRepository.findByMemberId(memberId);
        List<DonationHistories> donationHistoryRepositoryList = donationHistoryRepository.findByMemberId(memberId);

        List<HistoryResponseDTO> result = new ArrayList<>();
        HashMap<Long, Integer> map = new HashMap<>();

        int i = 0;
        for(Histories h : historyList){
            map.put(h.getHistoryId(),i++);
            HistoryResponseDTO dto = new HistoryResponseDTO(h.getHistoryId(),h.getHistoryType(),h.getTotalAmount(),h.getHistoryDate());
            result.add(dto);
        }

        for(ChargeHistories h : chargeHistoryRepositoryList){
            int index = map.get(h.getHistoryId());
            if(result.get(index).getType().equals("충전"))
                result.get(index).setTitle("이음페이머니");

            HistoryDTO dto = HistoryDTO.builder()
                    .type("충전")
                    .name("이음페이머니")
                    .price(h.getChargeAmount())
                    .build();
            result.get(index).getDetail().add(dto);
        }

        for(WithdrawalHistories h : withdrawalHistoryList){
            int index = map.get(h.getHistoryId());
            if(result.get(index).getType().equals("출금"))
                result.get(index).setTitle(h.getWithdrawalBrief());
            HistoryDTO dto = HistoryDTO.builder()
                    .type("출금")
                    .name(h.getWithdrawalBrief())
                    .price(h.getTransactionAmount())
                    .build();
            result.get(index).getDetail().add(dto);
        }

        for(DepositHistories h : depositHistoryRepositoryList){
            int index = map.get(h.getHistoryId());

            if(result.get(index).getType().equals("입금"))
                result.get(index).setTitle(h.getDepositBrief());

            HistoryDTO dto = HistoryDTO.builder()
                    .type("입금")
                    .name(h.getDepositBrief())
                    .price(h.getTransactionAmount())
                    .build();
            result.get(index).getDetail().add(dto);
        }



        for(PaymentHistoriesDTO h : paymentHistoryRepositoryList){
            int index = map.get(h.getHisid());

            if(result.get(index).getType().equals("결제"))
                result.get(index).setTitle(h.getSname());

            HistoryDTO dto = HistoryDTO.builder()
                    .type("결제")
                    .name(h.getSname())
                    .price(h.getAmount())
                    .build();
            result.get(index).getDetail().add(dto);
        }

        for(DonationHistories h : donationHistoryRepositoryList){
            int index = map.get(h.getHistoryId());

            if(result.get(index).getType().equals("기부"))
                result.get(index).setTitle("직접 기부");

            HistoryDTO dto = HistoryDTO.builder()
                    .type("기부")
                    .name(h.getFundingId().toString())
                    .price(h.getDonationAmount())
                    .build();
            result.get(index).getDetail().add(dto);
        }

        return result;
    }
}
