package com.ieum.common.controller;

import com.ieum.common.request.MmsRequestDTO;
import com.ieum.common.response.MmsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mms")
@Tag(name = "mms", description = "MMS API - 목업")
public class MmsController {
    @Operation(summary = "MMS 요청", description = "MMS를 요청합니다.")
    @ApiResponse(responseCode = "200", description = "MMS 요청 성공 - 인증키 반환")
    @PostMapping
    public ResponseEntity<MmsResponseDTO> requestMms(@RequestBody MmsRequestDTO request) {

        String phoneNumber = request.getPhoneNumber();
        String authCode = request.getAuthCode();
        // redis에서 phoneNumber의 value와 authCode 일치 확인
        String value = "";

        if (authCode.equals(value)) {
            MmsResponseDTO response = MmsResponseDTO.builder()
                .mmsAuth(true)
                .build();

            return ResponseEntity.ok(response);
        }

        MmsResponseDTO response = MmsResponseDTO.builder()
            .mmsAuth(false)
            .build();

        return ResponseEntity.ok(response);
    }


}
