package com.ieum.pay.service;

import com.ieum.pay.domain.Paymoney;
import com.ieum.pay.repository.PaymoneyRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;

@Service
@Transactional
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

        int sub = payAmount - money;
        int chargeAmount = sub / 10000 * 10000;
        if(sub > chargeAmount)
            chargeAmount += 10000;
        return chargeAmount;
    }

    public void signMember(Long memberId,String pwd) {
        paymoneyRepository.save(Paymoney.builder()
                .memberId(memberId).paymentPassword(pwd)
                .paymoneyAmount(0)
                .donationTotalAmount(0)
                .donationCount(0)
                .build());
    }
}
