package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.dto.HistoryDTO;
import com.ieum.common.dto.request.PayRemittancePaymoneyRequestDTO;
import com.ieum.common.dto.response.PayBalanceResponseDTO;
import com.ieum.common.dto.response.PayHistoryPeriodResponseDTO;
import com.ieum.common.dto.response.PayHistoryRemittanceResponseDTO;
import com.ieum.common.dto.response.PayRemittancePaymoneyResponseDTO;
import com.ieum.common.format.code.SuccessCode;
import com.ieum.common.format.response.ResponseTemplate;
import com.ieum.common.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "pay", description = "페이 API - 목업")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pay")
public class PayController {

    private final PayService payService;
    private final ResponseTemplate response;


    @Operation(summary = "메인 페이지 조회", description = "사용자의 카드 정보와 페이머니, 기부 총액 조회")
    @ApiResponse(responseCode = "200", description = "메인 페이지 조회 성공")
    @GetMapping("/main")
    public ResponseEntity<?> getMainPage(@CurrentMemberId Long memberId) {
        return response.success(payService.getMainPageInfo(memberId), SuccessCode.MAIN_PAGE_FETCH_SUCCESSFUL);
    }

    @Operation(summary = "페이먼트 잔액 조회", description = "사용자의 페이머트 잔액을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "페이먼트 잔액 조회 성공")
    @GetMapping("balance")
    public ResponseEntity<?> getPaymoney(@CurrentMemberId Long memberId) {
        return response.success(payService.nowMyPaymoney(memberId), SuccessCode.SUCCESS);

    }

    @Operation(summary = "사용 내역 조회", description = "지정된 기간 동안의 사용 내역을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "사용 내역 조회 성공")
    @GetMapping("history/{period}")
    public ResponseEntity<?> getHistoryList(@CurrentMemberId Long memberId, @PathVariable("period") String period) {
        List<PayHistoryPeriodResponseDTO> result = new ArrayList<>();
        HistoryDTO historyDetail1 = HistoryDTO.builder()
                                              .type("충전")
                                              .name("이음페이머니")
                                              .price(10000)
                                              .build();

        HistoryDTO historyDetail2 = HistoryDTO.builder()
                                              .type("결제")
                                              .name("COOPANG")
                                              .price(500)
                                              .build();

        HistoryDTO historyDetail3 = HistoryDTO.builder()
                                              .type("기부")
                                              .name("btc 아동센터")
                                              .price(400)
                                              .build();

        List<HistoryDTO> details = Arrays.asList(historyDetail1, historyDetail2, historyDetail3);

        PayHistoryPeriodResponseDTO history1 = PayHistoryPeriodResponseDTO.builder()
                                                                          .historyId(1L)
                                                                          .type("결제")
                                                                          .title("쿠팡")
                                                                          .amount(900)
                                                                          .detail(details)
                                                                          .build();

        HistoryDTO historyDetail4 = HistoryDTO.builder()
                                              .type("기부")
                                              .name("치킨이 먹고싶은 김범수")
                                              .price(900)
                                              .build();
        List<HistoryDTO> details1 = Arrays.asList(historyDetail4);

        PayHistoryPeriodResponseDTO history2 = PayHistoryPeriodResponseDTO.builder()
                                                                          .historyId(2L)
                                                                          .type("기부")
                                                                          .title("치킨이 먹고싶은 김범수")
                                                                          .amount(900)
                                                                          .detail(details1)
                                                                          .build();
        result.add(history1);
        result.add(history2);
        HistoryDTO historyDetail5 = HistoryDTO.builder()
                                              .type("입금")
                                              .name("고수형")
                                              .price(90000)
                                              .build();
        List<HistoryDTO> details2 = Arrays.asList(historyDetail5);

        PayHistoryPeriodResponseDTO history3 = PayHistoryPeriodResponseDTO.builder()
                                                                          .historyId(3L)
                                                                          .type("입금")
                                                                          .title("고수형")
                                                                          .amount(90000)
                                                                          .detail(details2)
                                                                          .build();
        result.add(history3);
        HistoryDTO historyDetail6 = HistoryDTO.builder()
                                              .type("송금")
                                              .name("고예지")
                                              .price(1000)
                                              .build();
        List<HistoryDTO> details3 = Arrays.asList(historyDetail6);

        PayHistoryPeriodResponseDTO history4 = PayHistoryPeriodResponseDTO.builder()
                                                                          .historyId(4L)
                                                                          .type("송금")
                                                                          .title("고예지")
                                                                          .amount(1000)
                                                                          .detail(details3)
                                                                          .build();
        result.add(history4);

        HistoryDTO historyDetail7 = HistoryDTO.builder()
                                              .type("충전")
                                              .name("이음페이머니")
                                              .price(10000)
                                              .build();
        List<HistoryDTO> details4 = Arrays.asList(historyDetail7);

        PayHistoryPeriodResponseDTO history5 = PayHistoryPeriodResponseDTO.builder()
                                                                          .historyId(5L)
                                                                          .type("충전")
                                                                          .title("이음페이머니")
                                                                          .amount(10000)
                                                                          .detail(details4)
                                                                          .build();
        result.add(history5);
        return response.success(payService.getHistoryList(memberId), SuccessCode.SUCCESS);
    }

    @Operation(summary = "송금 내역 조회", description = "특정 송금 내역을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "송금 내역 조회 성공")
    @GetMapping("history/remittance/{historyId}")
    public ResponseEntity<?> getHistory(@CurrentMemberId Long memberId, @PathVariable("historyId") Long id) {
        var result = PayHistoryRemittanceResponseDTO.builder()
                                                    .name("김범수")
                                                    .amount(1000)
                                                    .build();
        return response.success(result, SuccessCode.SUCCESS);
    }

    @Operation(summary = "페이머니 송금", description = "페이머니를 송금합니다.")
    @ApiResponse(responseCode = "200", description = "페이머니 송금 성공")
    @PostMapping("remittance/paymoney")
    public ResponseEntity<?> sendPaymoney(@RequestBody PayRemittancePaymoneyRequestDTO request) {
        var result = PayRemittancePaymoneyResponseDTO.builder()
                                                     .historyId(1L)
                                                     .build();
        return response.success(result, SuccessCode.SUCCESS);
    }

}
