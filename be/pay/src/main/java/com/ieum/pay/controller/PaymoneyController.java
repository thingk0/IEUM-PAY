package com.ieum.pay.controller;

import com.ieum.pay.service.PaymoneyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paymoney")
@RequiredArgsConstructor
public class PaymoneyController {
    private final PaymoneyService paymoneyService;
    @GetMapping("{memberId}")
    public int myPaymoney(@PathVariable long memberId){
        return paymoneyService.readPaymoney(memberId);
    }
}
