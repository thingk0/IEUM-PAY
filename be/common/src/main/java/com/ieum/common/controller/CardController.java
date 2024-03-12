package com.ieum.common.controller;

import com.ieum.common.request.CardMainRequestDTO;
import com.ieum.common.request.CardRegisterRequestDTO;
import com.ieum.common.request.CardUpdateRequestDTO;
import com.ieum.common.response.CardOcrResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/card")
public class CardController {
    @PutMapping("/main")
    public ResponseEntity<HttpStatus> cardMain(@RequestBody CardMainRequestDTO cardMainRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }
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

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> cardRegister(@RequestBody CardRegisterRequestDTO cardRegisterRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> cardUpdate(@RequestBody CardUpdateRequestDTO cardUpdateRequestDTO){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
