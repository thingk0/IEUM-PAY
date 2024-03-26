package com.ieum.pay.controller;

import com.ieum.pay.request.MemberPayPasswordRequestDTO;
import com.ieum.pay.request.RegisterRequestDTO;
import com.ieum.pay.service.PaymoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final PaymoneyService paymoneyService;

    @PostMapping("/register")
    public boolean signUp(@RequestBody RegisterRequestDTO requestDTO){
        return paymoneyService.signMember(requestDTO.getMemberId(), requestDTO.getPaymentPassword());
    }

    @PutMapping("pay-pw")
    public boolean updatePayPassword(@RequestBody MemberPayPasswordRequestDTO requestDTO){
        return paymoneyService.updatePayPassword(requestDTO.getMemberId(),requestDTO.getPaymentPassword());
    }
}
