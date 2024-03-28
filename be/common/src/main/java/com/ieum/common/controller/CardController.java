package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.domain.Members;
import com.ieum.common.dto.request.CardMainRequestDTO;
import com.ieum.common.dto.request.CardRegisterRequestDTO;
import com.ieum.common.dto.request.CardUpdateRequestDTO;
import com.ieum.common.dto.response.CardOcrResponseDTO;
import com.ieum.common.format.code.FailedCode;
import com.ieum.common.format.code.SuccessCode;
import com.ieum.common.format.response.ResponseTemplate;
import com.ieum.common.service.MemberService;
import com.ieum.common.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.ieum.common.format.code.FailedCode.PAYMENT_REGISTERED_CARD_NULL;
import static com.ieum.common.format.code.FailedCode.REGISTERED_CARD_DELETE;

@Tag(name = "card", description = "card API - 목업")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/card")
public class CardController {

    private final PayService payService;
    private final MemberService memberService;
    private final ResponseTemplate response;


    @Operation(summary = "메인 카드 설정", description = "사용자의 메인 카드를 설정합니다.")
    @ApiResponse(responseCode = "200", description = "메인 카드 설정 성공")
    @PutMapping("/main")
    public ResponseEntity<?> cardMain(@RequestBody CardMainRequestDTO requestDTO,
                                      @CurrentMemberId Long memberId) {
        if(payService.checkMyCard(memberId,requestDTO.getRegisterCardId())){
            memberService.mainCardUpdate(memberId,requestDTO.getRegisterCardId());
            return response.success(HttpStatus.OK);
        }
        return response.success(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "카드 OCR 처리", description = "카드 이미지를 스캔하여 정보를 추출합니다.")
    @ApiResponse(responseCode = "200", description = "OCR 처리 성공 - OCR 통해 읽은 카드 정보 반환")
    @PostMapping("/ocr")
    public ResponseEntity<?> cardOcr(@RequestParam("img") MultipartFile img) {
        CardOcrResponseDTO cardOcrResponseDTO = payService.getOcr(img);
        return response.success(payService.getOcr(img),
                SuccessCode.SUCCESS);
    }

    @Operation(summary = "카드 등록", description = "새로운 카드를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "카드 등록 성공")
    @ApiResponse(responseCode = "500", description = "카드 등록 실패")
    @PostMapping("/register")
    public ResponseEntity<?> cardRegister(@RequestBody CardRegisterRequestDTO requestDTO,
    @CurrentMemberId Long memberId) {
        Long id = payService.cardRegister(memberId, requestDTO.getCardNumber(), requestDTO.getCardNickname());

        if (id == -1L) {
            return response.success(id,
                    SuccessCode.CARD_VALID_WRONG);
        } else if (id == -2L) {
            return response.success(id,
                    SuccessCode.CARD_TYPE_WRONG);
        }
        Members member = memberService.findMemberById(memberId);
        if(member.getPaycardId() == null){
            memberService.mainCardUpdate(memberId,id);
        }
        return response.success(id,
                SuccessCode.CARD_REGISTER_SUCCESSFUL);

    }

    @Operation(summary = "카드 삭체", description = "카드 정보를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "카드 삭제 성공")
    @PutMapping("/update")
    public ResponseEntity<?> cardUpdate(@RequestBody CardUpdateRequestDTO requestDTO,
                                        @CurrentMemberId Long memberId) {
        Members member = memberService.findMemberById(memberId);
        if(member.getPaycardId() == requestDTO.getRegisteredCardId()){
            return response.error(REGISTERED_CARD_DELETE);
        }
        return response.success(payService.updateCard(memberId,requestDTO.getRegisteredCardId()),
               SuccessCode.SUCCESS);
    }
}
