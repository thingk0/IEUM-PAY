package com.ieum.common.controller;

import com.ieum.common.request.MemberLoginRequestDTO;
import com.ieum.common.request.MemberLoginpwUpdateRequestDTO;
import com.ieum.common.request.MemberNicknameRequestDTO;
import com.ieum.common.request.MemberPaypwUpdateRequestDTO;
import com.ieum.common.request.MemberRegistRequestDTO;
import com.ieum.common.request.MemberSearchRequestDTO;
import com.ieum.common.response.FundingInfoDTO;
import com.ieum.common.response.MemberExistResponseDTO;
import com.ieum.common.response.MemberLoginResponseDTO;
import com.ieum.common.response.MemberRegistResponseDTO;
import com.ieum.common.response.MemberResponseDTO;
import com.ieum.common.response.MemberSearchResponseDTO;
import com.ieum.common.response.MemberSummaryResponseDTO;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/member")
public class MemberController {

    @GetMapping()
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

    @PostMapping("/regist")
    public ResponseEntity<MemberRegistResponseDTO> registMember (@RequestBody MemberRegistRequestDTO request)
    {
        MemberRegistResponseDTO response = MemberRegistResponseDTO.builder()
            .memberId(1L)
            .name("김범수")
            .nickname("월평동상남자")
            .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/delete")
    public ResponseEntity<HttpStatus> deleteMember (@RequestBody String authenticationKey
            // @AuthenticationPrincipal Long memberId
        ) {
        // 더미데이터 AuthenticationKey - 1 들어오면 성공 1이외 다른 값이 들어오면 실패
        if (!"ok".equals(authenticationKey)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/exist")
    public ResponseEntity<MemberExistResponseDTO> checkMember (@RequestBody String phoneNumber) {

        // 번호 010-1234-1234 가 들어오면 멤버 존재
        if (phoneNumber.equals("010-1234-1234")) {
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

    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDTO> login (@RequestBody MemberLoginRequestDTO request) {
        MemberLoginResponseDTO response = MemberLoginResponseDTO.builder()
            .memberId(1L)
            .name("김범수")
            .nickname("예비군 간 범수")
            .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/login-pw/update")
    public ResponseEntity<HttpStatus> updateLoginPassword (@RequestBody MemberLoginpwUpdateRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/nickname")
    public ResponseEntity<HttpStatus> updateNickname (@RequestBody MemberNicknameRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/pay-pw/update")
    public ResponseEntity<HttpStatus> updatePaymentPassword (@RequestBody MemberPaypwUpdateRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<MemberSearchResponseDTO> searchMember (@RequestBody MemberSearchRequestDTO request)
    {
        MemberSearchResponseDTO response = MemberSearchResponseDTO.builder()
            .name("김범수")
            .phoneNumber("010-1234-1234")
            .build();

        return ResponseEntity.ok(response);
    }

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
