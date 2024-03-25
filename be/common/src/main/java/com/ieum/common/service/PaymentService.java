package com.ieum.common.service;

import com.ieum.common.feign.PayFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PayFeignClient payFeignClient;


    public void processPayment() {
    }

    public void verifyPaymentPassword() {

    }

    public void updatePaymentPassword() {

    }

    public void getPaymentHistory() {

    }

    public void getPaymentInfo() {

    }
}
