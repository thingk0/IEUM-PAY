package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.dto.request.paymentPasswordRequestDTO;
import com.ieum.common.dto.request.secondaryAuthRequestDTO;
import com.ieum.common.dto.response.secondaryAuthResponseDTO;
import com.ieum.common.format.code.SuccessCode;
import com.ieum.common.format.response.ResponseTemplate;
import com.ieum.common.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth", description = "인증 API - 목업")
@Validated
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ResponseTemplate response;
    private final StringRedisTemplate stringRedisTemplate;
    private final AuthService authService;

    @Operation(summary = "본인 확인용 인증 메시지 발급", description = "본인 확인 메시지 요청을 받아 인증 코드를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "본인 확인 메시지용 코드 반환")
    @GetMapping("/api/auth")
    public ResponseEntity<?> getAuthenticationCode(@RequestParam("phone-number")
                                                   @NotBlank(message = "휴대폰 번호는 필수입니다.")
                                                   @Pattern(regexp = "^010\\d{8}$", message = "휴대폰 번호 형식이 잘못되었습니다.")
                                                   String phoneNumber) {

        String code = "code:" + UUID.randomUUID() + "%";
        stringRedisTemplate.opsForValue().set("phone-number:" + phoneNumber,
                                              code, 5, TimeUnit.MINUTES);
        return response.success(code, SuccessCode.AUTHENTICATION_CODE_ISSUED);
    }

    @Operation(summary = "2차 비밀번호 인증", description = "2차 비밀번호 인증 요청")
    @ApiResponse(responseCode = "200", description = "인증키, 성공여부 반환")
    @PostMapping("/api/auth/password")
    public ResponseEntity<?> secondaryAuthentication(@RequestBody paymentPasswordRequestDTO req,
        @CurrentMemberId Long memberId) {
        secondaryAuthRequestDTO reqDto = secondaryAuthRequestDTO.builder()
            .paymentPassword(req.getPaymentPassword())
            .memberId(memberId)
            .build();

        secondaryAuthResponseDTO res = authService.secondaryAuthentication(reqDto);
        return response.success(res, SuccessCode.AUTHENTICATION_CODE_ISSUED);
    }

}
