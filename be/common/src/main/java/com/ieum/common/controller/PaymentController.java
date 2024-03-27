package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.dto.request.MemberPaypwUpdateRequestDTO;
import com.ieum.common.dto.request.PaymentRequestDTO;
import com.ieum.common.dto.response.PaymentInfoResponseDTO;
import com.ieum.common.format.code.SuccessCode;
import com.ieum.common.format.response.ResponseTemplate;
import com.ieum.common.service.PayService;
import com.ieum.common.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "payment", description = "결제 API - 목업")
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PayService payService;
    private final ResponseTemplate response;

    @Operation(summary = "결제 처리", description = "결제를 처리합니다.")
    @ApiResponse(responseCode = "200", description = "결제 처리 성공")
    @PostMapping
    public ResponseEntity<?> payment(@RequestBody PaymentRequestDTO requestDTO,
                                     @CurrentMemberId Long memberId) {

        paymentService.processPayment();
        return response.success(HttpStatus.OK);
    }

    @Operation(summary = "결제 비밀번호 확인", description = "회원의 결제 비밀번호 확인 - 수정 필요")
    @ApiResponse(responseCode = "200", description = "결제 비밀번호 인증 성공")
    @GetMapping("/password/verify")
    public ResponseEntity<?> checkPaymentPassword(@CurrentMemberId Long memberId) {

        paymentService.verifyPaymentPassword();
        return response.success(HttpStatus.OK);
    }


    @Operation(summary = "결제 비밀번호 변경", description = "회원의 결제 비밀번호를 변경합니다. - 수정 필요")
    @ApiResponse(responseCode = "200", description = "결제 비밀번호 변경 성공")
    @PutMapping("/pay-pw/update")
    public ResponseEntity<?> updatePaymentPassword(@RequestBody MemberPaypwUpdateRequestDTO request,
                                                   @CurrentMemberId Long memberId) {

        paymentService.updatePaymentPassword();
        return response.success(HttpStatus.OK);
    }

    @Operation(summary = "결제 내역 조회", description = "특정 결제 내역을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "결제 내역 조회 성공")
    @GetMapping("{historyId}")
    public ResponseEntity<?> getPaymentHistory(@PathVariable("historyId") Long id,
                                               @CurrentMemberId Long memberId) {

        paymentService.getPaymentHistory();
        return response.success(payService.getPaymentHistory(memberId,id), SuccessCode.SUCCESS);

    }

    @Operation(summary = "결제 정보 조회", description = "특정 매장과 가격에 따른 결제 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "결제 정보 조회 성공")
    @GetMapping("info/{store}/{price}")
    public ResponseEntity<PaymentInfoResponseDTO> getPaymentInfo(@PathVariable("store") String store,
                                                                 @PathVariable("price") int price,
                                                                 @CurrentMemberId Long memberId) {

        paymentService.getPaymentInfo();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
