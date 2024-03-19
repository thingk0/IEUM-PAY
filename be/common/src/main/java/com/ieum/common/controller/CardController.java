package com.ieum.common.controller;

import com.ieum.common.request.CardMainRequestDTO;
import com.ieum.common.request.CardRegisterRequesterDTO;
import com.ieum.common.request.CardUpdateRequestDTO;
import com.ieum.common.response.CardOcrResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/card")
@Tag(name = "card", description = "card API - 목업")
public class CardController {
    @Operation(summary = "메인 카드 설정", description = "사용자의 메인 카드를 설정합니다.")
    @ApiResponse(responseCode = "200", description = "메인 카드 설정 성공")
    @PutMapping("/main")
    public ResponseEntity<HttpStatus> cardMain(@RequestBody CardMainRequestDTO cardMainRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "카드 OCR 처리", description = "카드 이미지를 스캔하여 정보를 추출합니다.")
    @ApiResponse(responseCode = "200", description = "OCR 처리 성공 - OCR 통해 읽은 카드 정보 반환")
    @PostMapping("/ocr")
    public ResponseEntity<CardOcrResponseDTO> cardOcr(@RequestParam("img") MultipartFile img) {
        // 파일의 확장자 추출
        String originalFileExtension;
        String contentType = img.getContentType();

        if (!ObjectUtils.isEmpty(contentType)) {
            if (contentType.contains("image/jpeg"))
                originalFileExtension = ".jpg";
            else if (contentType.contains("image/png"))
                originalFileExtension = ".png";
        }
        CardOcrResponseDTO cardOcrResponseDTO = CardOcrResponseDTO.builder()
                .cardNumber("1234-1234-1234-1234")
                .cardCvc("123")
                .validThru("02/28")
                .build();
        return new ResponseEntity<>(cardOcrResponseDTO,HttpStatus.OK);
    }

    @Operation(summary = "카드 등록", description = "새로운 카드를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "카드 등록 성공")
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> cardRegister(@RequestBody CardRegisterRequesterDTO cardRegisterRequesterDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "카드 정보 업데이트", description = "기존에 등록된 카드 정보를 업데이트합니다.")
    @ApiResponse(responseCode = "200", description = "카드 정보 업데이트 성공")
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> cardUpdate(@RequestBody CardUpdateRequestDTO cardUpdateRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
