package com.ieum.common.controller;

import com.ieum.common.dto.HistoryDTO;
import com.ieum.common.request.PayRemittancePaymoneyRequestDTO;
import com.ieum.common.response.PayBalanceResponseDTO;
import com.ieum.common.response.PayHistoryPeriodResponseDTO;
import com.ieum.common.response.PayHistoryRemittanceResponseDTO;
import com.ieum.common.response.PayRemittancePaymoneyResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/pay")
public class PayController {
    @GetMapping("balance")
    public ResponseEntity<PayBalanceResponseDTO> getPaymoney (){
        PayBalanceResponseDTO result = PayBalanceResponseDTO.builder()
                .paymoneyAmount(3000)
                .build();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("history/{period}")
    public ResponseEntity<List<PayHistoryPeriodResponseDTO>> getHistoryList(@PathVariable("period") String period){
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
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping("history/remittance/{historyId}")
    public ResponseEntity<PayHistoryRemittanceResponseDTO> getHistory(@PathVariable("historyId") Long id){
        PayHistoryRemittanceResponseDTO result = PayHistoryRemittanceResponseDTO.builder()
                .name("김범수")
                .amount(1000)
                .build();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PostMapping("remittance/paymoney")
    public ResponseEntity<PayRemittancePaymoneyResponseDTO> sendPaymoney(@RequestBody PayRemittancePaymoneyRequestDTO request){
        PayRemittancePaymoneyResponseDTO result = PayRemittancePaymoneyResponseDTO.builder()
                .historyId(1L)
                .build();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
