package com.ieum.pay.controller;

import com.ieum.pay.request.RegisterRequestDTO;
import com.ieum.pay.service.PaymoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final PaymoneyService paymoneyService;

    @PostMapping("/register")
    public void signUp(@RequestBody RegisterRequestDTO requestDTO){
        paymoneyService.signMember(requestDTO.getMemberId(), requestDTO.getPaymentPassword());
    }
}
