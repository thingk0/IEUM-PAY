package com.ieum.common.controller;

import com.ieum.common.request.MmsRequestDTO;
import com.ieum.common.response.MmsResponseDTO;
import java.text.SimpleDateFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mms")
public class MmsController {
    @PostMapping()
    public ResponseEntity<MmsResponseDTO> requestMms(@RequestBody MmsRequestDTO request) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        MmsResponseDTO response = MmsResponseDTO.builder()
            .mmsAuth("QWERASDZXCASDQWEQWE123124")
            .build();

        return ResponseEntity.ok(response);
    }


}
