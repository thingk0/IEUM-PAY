package com.ieum.common.feign;

import com.ieum.common.request.CardRegisterRequestDTO;
import com.ieum.common.response.CardOcrResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "pay", url = "${gateway.pay}")
public interface PayFeignClient {
    @PostMapping(value = "/card/ocr",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    CardOcrResponseDTO cardOcr(@RequestBody MultipartFile img);

    @PostMapping("/card/valid")
    Long cardValid(@RequestBody CardRegisterRequestDTO cardNumber);

    @PutMapping("/card/delete")
    void cardDelete(Long id);
}
