package com.ieum.common.controller;

import com.ieum.common.format.code.SuccessCode;
import com.ieum.common.format.response.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth", description = "인증 API - 목업")
@Validated
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ResponseTemplate response;
    private final StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "본인 확인용 인증 메시지 발급", description = "본인 확인 메시지 요청을 받아 인증 코드를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "본인 확인 메시지용 코드 반환")
    @GetMapping("/api/auth")
    public ResponseEntity<?> getAuthenticationCode(@RequestParam("phone-number")
                                                   @NotBlank(message = "휴대폰 번호는 필수입니다.")
                                                   String phoneNumber) {

        String code = "code:" + UUID.randomUUID() + "%";
        stringRedisTemplate.opsForValue().set("phone-number:" + phoneNumber,
                                              code, 5, TimeUnit.MINUTES);
        return response.success(code, SuccessCode.PAYMENT_INITIATED);
    }

}
