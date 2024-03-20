package com.ieum.common.controller;

import com.ieum.common.request.MmsAuthRequestDTO;
import com.ieum.common.request.MmsCheckRequestDTO;
import com.ieum.common.response.MmsAuthResponseDTO;
import com.ieum.common.response.MmsCheckResponseDTO;
import com.ieum.common.service.MmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mms")
@Tag(name = "mms", description = "MMS API - 목업")
@AllArgsConstructor
public class MmsController {

    private final StringRedisTemplate stringRedisTemplate;
    private MmsService mmsService;
    @Operation(summary = "MMS 확인", description = "MMS 인증 확인")
    @ApiResponse(responseCode = "200", description = "MMS 인증 true/false")
    @PostMapping("/check")
    public ResponseEntity<MmsCheckResponseDTO> checkMms(@RequestBody MmsCheckRequestDTO request) {

        String phoneNumber = request.getPhoneNumber();

        String storedAuthCode = stringRedisTemplate.opsForValue().get("mmsAuth:" + phoneNumber);

        if(storedAuthCode != null){
            MmsCheckResponseDTO response = MmsCheckResponseDTO.builder()
                .authCheck(true)
                .build();

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        MmsCheckResponseDTO response = MmsCheckResponseDTO.builder()
            .authCheck(false)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "MMS 인증 코드 요청", description = "MMS 인증 요청")
    @ApiResponse(responseCode = "200", description = "MMS 인증 코드 반환")
    @PostMapping("/auth")
    public ResponseEntity<MmsAuthResponseDTO> requestMms(@RequestBody MmsAuthRequestDTO request) {

        String phoneNumber = request.getPhoneNumber();
        String code = mmsService.getAuthenticationCode(phoneNumber);

        MmsAuthResponseDTO response = MmsAuthResponseDTO.builder()
            .mmsAuth(code)
            .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
