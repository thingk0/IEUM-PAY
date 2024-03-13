package com.ieum.common.controller;

import com.ieum.common.request.PaymentRequestDTO;
import com.ieum.common.response.PaymentHistoryResponseDTO;
import com.ieum.common.response.PaymentInfoResponseDTO;
import com.ieum.common.response.PaymentResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> payment(@RequestBody PaymentRequestDTO requestDTO){
        PaymentResponseDTO responseDTO = PaymentResponseDTO.builder()
                .historyId(1L).build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("{historyId}")
    public ResponseEntity<PaymentHistoryResponseDTO> getPaymentHistory(@PathVariable("historyId") Long id){
        PaymentHistoryResponseDTO responseDTO = PaymentHistoryResponseDTO.builder()
                .storeName("삼성몰")
                .paymentAmount(4500)
                .donationAmount(500)
                .facilityName("btc")
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("info/{store}/{price}")
    public ResponseEntity<PaymentInfoResponseDTO> getPaymentInfo(@PathVariable("store") String store, @PathVariable("price") int price){
        PaymentInfoResponseDTO responseDTO = PaymentInfoResponseDTO.builder()
                .storeId(1L)
                .price(5000)
                .storeName("삼성몰")
                .paymoneyAmount(400)
                .chargeAmount(10000)
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
