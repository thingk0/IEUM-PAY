package com.ieum.pay.service;

import com.ieum.pay.domain.Paymoney;
import com.ieum.pay.repository.PaymoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymoneyService {
    private final PaymoneyRepository paymoneyRepository;
    public void updatePaymonyAmount(int isCome, Long sederId, int moveMoney) {
        Paymoney paymoney = paymoneyRepository.findByMemberId(sederId);
        int amount = paymoney.getPaymoneyAmount();
        if(isCome == 1){
            paymoney.setPaymoneyAmount(amount + moveMoney);
        }else {
            paymoney.setPaymoneyAmount(amount - moveMoney);
        }
        paymoneyRepository.save(paymoney);
    }

    //얼마나 충전이 필요한지 확인하는 함수
    public int chargeMoney(Long memberId, int money) {
        Paymoney paymoney = paymoneyRepository.findByMemberId(memberId);
        int payAmount = paymoney.getPaymoneyAmount();
        if(money <= payAmount)
            return 0;

        int sub = money - payAmount;
        int chargeAmount = sub / 10000 * 10000;
        if(sub > chargeAmount)
            chargeAmount += 10000;
        return chargeAmount;
    }

    public boolean signMember(Long memberId, String pwd) {
        try {
            // Paymoney 객체를 생성하고 저장
            Paymoney savedPaymoney = paymoneyRepository.save(Paymoney.builder()
                    .memberId(memberId)
                    .paymentPassword(pwd)
                    .paymoneyAmount(0)
                    .donationTotalAmount(0)
                    .donationCount(0)
                    .build());

            // 저장 성공
            return true;
        } catch (Exception e) {
            // 저장 중 예외 발생, 실패 처리
            return false;
        }
    }


    public int readPaymoney(long memberId) {
        Paymoney paymoney = paymoneyRepository.findByMemberId(memberId);
        return paymoney.getPaymoneyAmount();
    }

    public boolean updatePayPassword(Long memberId, String paymentPassword) {
        Paymoney paymoney = paymoneyRepository.findByMemberId(memberId);
        if(paymoney == null)
            return false;
        paymoney.setPaymentPassword(paymentPassword);
        return true;
    }

    public Paymoney getPaymoney(Long memberId) {
        return paymoneyRepository.findByMemberId(memberId);
    }
}
