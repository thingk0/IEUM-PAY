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
        Histories histories = Histories.builder()
                .memberId(senderId)
                .totalAmount(0)
                .historyType("출금")
                .historyDate(LocalDateTime.now())
                .build();
        Histories history = historyRepository.save(histories);

        WithdrawalHistories withdrawalhistories = WithdrawalHistories.builder()
                .historyId(history.getHistoryId())
                .memberId(senderId)
                .transactionAmount(amount)
                .withdrawalBrief(receiverName)
                .build();

        if(chargeMoney > 0){
            ChargeHistories chargehistories = ChargeHistories.builder()
                    .registeredCardId(cardId)
                    .historyId(history.getHistoryId())
                    .memberId(senderId)
                    .chargeAmount(chargeMoney)
                    .build();
            chargeHistoryRepository.save(chargehistories);
        }

        withdrawalHistoryRepository.save(withdrawalhistories);
    }

    public void receiveMoney(Long receiverId, String senderName, int amount) {
        //사용자간 페이머니 송금시 출금순서
        Histories histories = Histories.builder()
                .memberId(receiverId)
                .totalAmount(0)
                .historyType("입금")
                .historyDate(LocalDateTime.now())
                .build();
        Histories history = historyRepository.save(histories);

        DepositHistories depositHistories = DepositHistories.builder()
                .historyId(history.getHistoryId())
                .memberId(receiverId)
                .transactionAmount(amount)
                .depositBrief(senderName)
                .build();

        depositHistoryRepository.save(depositHistories);
    }
}
