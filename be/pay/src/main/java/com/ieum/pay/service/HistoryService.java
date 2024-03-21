package com.ieum.pay.service;

import com.ieum.pay.domain.ChargeHistories;
import com.ieum.pay.domain.DepositHistories;
import com.ieum.pay.domain.Histories;
import com.ieum.pay.domain.WithdrawalHistories;
import com.ieum.pay.repository.ChargeHistoryRepository;
import com.ieum.pay.repository.DepositHistoryRepository;
import com.ieum.pay.repository.HistoryRepository;
import com.ieum.pay.repository.WithdrawalHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final WithdrawalHistoryRepository withdrawalHistoryRepository;
    private final DepositHistoryRepository depositHistoryRepository;
    private final ChargeHistoryRepository chargeHistoryRepository;

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

    public void payment(Long memberId, Long storeId, Long fundingId, Long cardId, int chargeAmount, int amount, int donationAmount) {

        //history
        Histories result = historySave(memberId,0,"출금");// 0 revise check
        //charge
        if(chargeAmount > 0){
            chargeHistorySave(result.getHistoryId(), memberId,cardId,chargeAmount);
        }
        //payment

        //donation

        //paymoney
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
}
