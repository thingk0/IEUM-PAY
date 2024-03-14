package com.ieum.common.controller;

import com.ieum.common.request.MemberDeleteRequestDTO;
import com.ieum.common.request.MemberExistRequestDTO;
import com.ieum.common.request.MemberLoginRequestDTO;
import com.ieum.common.request.MemberLoginpwUpdateRequestDTO;
import com.ieum.common.request.MemberNicknameRequestDTO;
import com.ieum.common.request.MemberPaypwUpdateRequestDTO;
import com.ieum.common.request.MemberRegisterRequestDTO;
import com.ieum.common.request.MemberSearchRequestDTO;
import com.ieum.common.dto.FundingInfoDTO;
import com.ieum.common.response.MemberExistResponseDTO;
import com.ieum.common.response.MemberLoginResponseDTO;
import com.ieum.common.response.MemberRegistResponseDTO;
import com.ieum.common.response.MemberResponseDTO;
import com.ieum.common.response.MemberSearchResponseDTO;
import com.ieum.common.response.MemberSummaryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@Tag(name = "members", description = "멤버 API - 목업")
public class MemberController {

    @Operation(summary = "회원 정보 조회", description = "회원의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공, 회원 정보 반환")
    @GetMapping
    public ResponseEntity<MemberResponseDTO> getMemberInfo (
        // @AuthenticationPrincipal Long memberId
    ) {
        MemberResponseDTO response = MemberResponseDTO.builder()
            .memberId(1L)
            .name("황정민")
            .nickname("hero")
            .gradeCode(2)
            .gradeName("새싹")
            .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "회원 등록 성공, 이름, 닉네임 반환")
    @PostMapping("/regist")
    public ResponseEntity<MemberRegistResponseDTO> registerMember(
        @RequestBody MemberRegisterRequestDTO request)
    {
        MemberRegistResponseDTO response = MemberRegistResponseDTO.builder()
            .memberId(1L)
            .name("김범수")
            .nickname("월평동상남자")
            .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "회원 삭제 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @PutMapping("/delete")
    public ResponseEntity<HttpStatus> deleteMember (@RequestBody MemberDeleteRequestDTO request
            // @AuthenticationPrincipal Long memberId
        ) {
        // 더미데이터 AuthenticationKey - 1 들어오면 성공 1이외 다른 값이 들어오면 실패
        if (!"ok".equals(request.getAuthenticationKey())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @Operation(summary = "회원 존재 여부 확인", description = "회원의 존재 여부를 확인합니다.")
    @ApiResponse(responseCode = "200", description = "회원 존재 여부 exist:true/false")
    @PostMapping("/exist")
    public ResponseEntity<MemberExistResponseDTO> checkMember (@RequestBody MemberExistRequestDTO request) {

        // 번호 010-1234-1234 가 들어오면 멤버 존재
        if (request.getPhoneNumber().equals("010-1234-1234")) {
            MemberExistResponseDTO response = MemberExistResponseDTO.builder()
                .exist(true)
                .build();
            return ResponseEntity.ok(response);
        }
        else {
            MemberExistResponseDTO response = MemberExistResponseDTO.builder()
                .exist(false)
                .build();
            return ResponseEntity.ok(response);
        }
    }

    @Operation(summary = "회원 로그인", description = "회원 로그인을 처리합니다.")
    @ApiResponse(responseCode = "200", description = "멤버ID, 이름, 닉네임 반환")
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDTO> login (@RequestBody MemberLoginRequestDTO request) {
        MemberLoginResponseDTO response = MemberLoginResponseDTO.builder()
            .memberId(1L)
            .name("김범수")
            .nickname("예비군 간 범수")
            .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "로그인 비밀번호 변경", description = "회원의 로그인 비밀번호를 변경합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 비밀번호 변경 성공")
    @PutMapping("/login-pw/update")
    public ResponseEntity<HttpStatus> updateLoginPassword (@RequestBody MemberLoginpwUpdateRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "닉네임 변경", description = "회원의 닉네임을 변경합니다.")
    @ApiResponse(responseCode = "200", description = "닉네임 변경 성공")
    @PutMapping("/nickname")
    public ResponseEntity<HttpStatus> updateNickname (@RequestBody MemberNicknameRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "결제 비밀번호 변경", description = "회원의 결제 비밀번호를 변경합니다.")
    @ApiResponse(responseCode = "200", description = "결제 비밀번호 변경 성공")
    @PutMapping("/pay-pw/update")
    public ResponseEntity<HttpStatus> updatePaymentPassword (@RequestBody MemberPaypwUpdateRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "회원 검색", description = "회원을 검색합니다.")
    @ApiResponse(responseCode = "200", description = "회원 검색 성공 - 회원 이름, 전화번호")
    @PostMapping("/search")
    public ResponseEntity<MemberSearchResponseDTO> searchMember (@RequestBody MemberSearchRequestDTO request)
    {
        MemberSearchResponseDTO response = MemberSearchResponseDTO.builder()
            .name("김범수")
            .phoneNumber("010-1234-1234")
            .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 요약 정보 조회", description = "회원의 요약 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "회원 정보 요약(기부내역 포함)")
    @PostMapping("/summary")
    public ResponseEntity<MemberSummaryResponseDTO> getMemberSummary (
        // @AuthenticationPrincipal Long memberId
    ) {
        FundingInfoDTO fundingInfo1 = FundingInfoDTO.builder()
            .img("http://url ---")
            .fundingId(1L)
            .fundingAmount(13300)
            .ongoing(false)
            .build();

        FundingInfoDTO fundingInfo2 = FundingInfoDTO.builder()
            .img("http://url ---")
            .fundingId(2L)
            .fundingAmount(3300)
            .ongoing(false)
            .build();

        FundingInfoDTO fundingInfo3 = FundingInfoDTO.builder()
            .img("http://url ---")
            .fundingId(3L)
            .fundingAmount(100)
            .ongoing(true)
            .build();

        MemberSummaryResponseDTO response = MemberSummaryResponseDTO.builder()
            .name("김싸피")
            .nickname("기부니가 좋아")
            .gradeCode(2)
            .gradeName("새싹")
            .totalDonationCnt(3)
            .totalDonationAmount(1122200)
            .fundingInfoList(Arrays.asList(fundingInfo1, fundingInfo2, fundingInfo3))
            .autoFundingId(3L)
            .autoFundingTitle("btc")
            .autoFundingImg("http://url ---")
            .autoFundingAmount(100)
            .build();

        return ResponseEntity.ok(response);
    }
}
