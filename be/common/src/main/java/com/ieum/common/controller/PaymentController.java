package com.ieum.common.controller;

import com.ieum.common.request.PaymentRequestDTO;
import com.ieum.common.response.PaymentHistoryResponseDTO;
import com.ieum.common.response.PaymentInfoResponseDTO;
import com.ieum.common.response.PaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@Tag(name = "payment", description = "결제 API - 목업")
public class PaymentController {
    @Operation(summary = "결제 처리", description = "결제를 처리합니다.")
    @ApiResponse(responseCode = "200", description = "결제 처리 성공")
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> payment(@RequestBody PaymentRequestDTO requestDTO){
        PaymentResponseDTO responseDTO = PaymentResponseDTO.builder()
                .historyId(1L).build();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "결제 내역 조회", description = "특정 결제 내역을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "결제 내역 조회 성공")
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

    @Operation(summary = "결제 정보 조회", description = "특정 매장과 가격에 따른 결제 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "결제 정보 조회 성공")
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
