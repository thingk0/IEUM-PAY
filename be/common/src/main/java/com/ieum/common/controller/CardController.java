package com.ieum.common.controller;

import com.ieum.common.request.CardMainRequestDTO;
import com.ieum.common.request.CardRegisterRequestDTO;
import com.ieum.common.request.CardUpdateRequestDTO;
import com.ieum.common.response.CardOcrResponseDTO;
import com.ieum.common.response.CardRegisterResponseDTO;
import com.ieum.common.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/card")
@RequiredArgsConstructor
@Tag(name = "card", description = "card API - 목업")
public class CardController {

    private final PayService payService;

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
        CardOcrResponseDTO cardOcrResponseDTO = payService.getOcr(img);
        return new ResponseEntity<>(cardOcrResponseDTO,HttpStatus.OK);
    }

    @Operation(summary = "카드 등록", description = "새로운 카드를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "카드 등록 성공")
    @ApiResponse(responseCode = "500", description = "카드 등록 실패")
    @PostMapping("/register")
    public ResponseEntity<CardRegisterResponseDTO> cardRegister(@RequestBody CardRegisterRequestDTO cardRegisterRequesterDTO){
        Long id = payService.cardRegister(cardRegisterRequesterDTO);
        CardRegisterResponseDTO responseDTO = CardRegisterResponseDTO.builder().cardId(id).build();

        if(id == -1L)
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        else if(id == -2L)
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Operation(summary = "카드 삭체", description = "카드 정보를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "카드 삭제 성공")
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> cardUpdate(@RequestBody CardUpdateRequestDTO cardUpdateRequestDTO){
        //auth check
        //card owner is user?
        payService.updateCard(cardUpdateRequestDTO.getRegisteredCardId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
