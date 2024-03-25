package com.ieum.common.controller;

import com.ieum.common.annotation.CurrentMemberId;
import com.ieum.common.domain.Members;
import com.ieum.common.dto.FundingInfoDTO;
import com.ieum.common.dto.member.LoginRequestDto;
import com.ieum.common.dto.member.SignupRequestDto;
import com.ieum.common.dto.request.MemberExistRequestDTO;
import com.ieum.common.dto.request.MemberLoginpwUpdateRequestDTO;
import com.ieum.common.dto.request.MemberNicknameRequestDTO;
import com.ieum.common.dto.request.MemberPaypwUpdateRequestDTO;
import com.ieum.common.dto.request.MemberSearchRequestDTO;
import com.ieum.common.dto.response.MemberExistResponseDTO;
import com.ieum.common.dto.response.MemberLoginResponseDTO;
import com.ieum.common.dto.response.MemberRegistResponseDTO;
import com.ieum.common.dto.member.ProfileResponseDto;
import com.ieum.common.dto.response.MemberSearchResponseDTO;
import com.ieum.common.dto.response.MemberSummaryResponseDTO;
import com.ieum.common.format.response.ResponseTemplate;
import com.ieum.common.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "members", description = "멤버 API - 목업")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;
    private final ResponseTemplate response;

    @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다.")
    @ApiResponse(responseCode = "200", description = "회원 등록 성공, 이름, 닉네임 반환")
    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody @Valid SignupRequestDto request) {
        memberService.signup(request);
        return response.success(MemberRegistResponseDTO.builder()
                                                       .memberId(1L)
                                                       .name("김범수")
                                                       .nickname("월평동상남자")
                                                       .build(), HttpStatus.OK);
    }

    @Operation(summary = "회원 정보 조회", description = "회원의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공, 회원 정보 반환")
    @GetMapping
    public ResponseEntity<?> getMemberInfo(@RequestBody @CurrentMemberId Long memberId) {
        return response.success(ProfileResponseDto.builder()
                                                  .memberId(1L)
                                                  .name("황정민")
                                                  .nickname("hero")
                                                  .gradeCode("GR002")
                                                  .gradeName("새싹")
                                                  .build(), HttpStatus.OK);
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 합니다.")
    @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @PutMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody @CurrentMemberId Long memberId) {
        return response.success(memberService.delete(memberId), HttpStatus.OK);
    }

    @Operation(summary = "회원 존재 여부 확인", description = "회원의 존재 여부를 확인합니다.")
    @ApiResponse(responseCode = "200", description = "회원 존재 여부 exist:true/false")
    @PostMapping("/exist")
    public ResponseEntity<MemberExistResponseDTO> checkMember(@RequestBody MemberExistRequestDTO request) {

        String phoneNumber = request.getPhoneNumber();
        Members member = memberService.getMemberByPhoneNumber(phoneNumber);

        if (member != null) {
            MemberExistResponseDTO response = MemberExistResponseDTO.builder()
                                                                    .exist(true)
                                                                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            MemberExistResponseDTO response = MemberExistResponseDTO.builder()
                                                                    .exist(false)
                                                                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @Operation(summary = "회원 로그인", description = "회원 로그인을 처리합니다.")
    @ApiResponse(responseCode = "200", description = "멤버ID, 이름, 닉네임 반환")
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDTO> login(@RequestBody LoginRequestDto request) {
        Members member = memberService.getMemberByPhoneNumberAndPassword(request.getPhoneNumber(), request.getPassword());

        if (member == null) {
            MemberLoginResponseDTO response = MemberLoginResponseDTO.builder()
                                                                    .memberId(-1L)
                                                                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        var response = MemberLoginResponseDTO.builder()
                                             .memberId(member.getId())
                                             .name(member.getName())
                                             .nickname(member.getNickname())
                                             .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "로그인 비밀번호 변경", description = "회원의 로그인 비밀번호를 변경합니다.")
    @ApiResponse(responseCode = "200", description = "로그인 비밀번호 변경 성공")
    @PutMapping("/login-pw/update")
    public ResponseEntity<?> updateLoginPassword(@RequestBody MemberLoginpwUpdateRequestDTO request,
                                                 Long memberId) {
        // TODO: 비밀번호 변경 서비스 호출
        return response.success(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "닉네임 변경", description = "회원의 닉네임을 변경합니다.")
    @ApiResponse(responseCode = "200", description = "닉네임 변경 성공")
    @PutMapping("/nickname")
    public ResponseEntity<?> updateNickname(@RequestBody MemberNicknameRequestDTO request,
                                            Long memberId) {

        // TODO: 비밀번호 변경 서비스 호출
        return response.success(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "결제 비밀번호 확인", description = "회원의 결제 비밀번호 확인 - 수정 필요")
    @ApiResponse(responseCode = "200", description = "결제 비밀번호 인증 성공")
    @PutMapping("/pay-pw/auth")
    public ResponseEntity<?> checkPaymentPassword(Long memberId) {

//        String authenticationKey = memberService.checkPaymentPassword(memberId, request.getPaymentPassword());
//        MemberPaypwAuthResponseDTO res = new MemberPaypwAuthResponseDTO();
//        res.setAuthenticationKey(authenticationKey);

        return response.success(HttpStatus.OK);
    }

    @Operation(summary = "결제 비밀번호 변경", description = "회원의 결제 비밀번호를 변경합니다. - 수정 필요")
    @ApiResponse(responseCode = "200", description = "결제 비밀번호 변경 성공")
    @PutMapping("/pay-pw/update")
    public ResponseEntity<?> updatePaymentPassword(@RequestBody MemberPaypwUpdateRequestDTO request,
                                                   Long memberId) {

        return response.success(HttpStatus.OK);
    }

    @Operation(summary = "회원 검색", description = "회원을 검색합니다.")
    @ApiResponse(responseCode = "200", description = "회원 검색 성공 - 회원 이름, 전화번호")
    @PostMapping("/search")
    public ResponseEntity<?> searchMember(@RequestBody MemberSearchRequestDTO request) {

        Members member = memberService.getMemberByPhoneNumber(request.getPhoneNumber());

        if (member == null) {
            var res = MemberSearchResponseDTO.builder().build();
            return response.success(res, HttpStatus.OK);

        }
        var res = MemberSearchResponseDTO.builder()
                                         .name(member.getName())
                                         .phoneNumber(member.getPhoneNumber())
                                         .build();

        return response.success(HttpStatus.OK);
    }

    @Operation(summary = "회원 요약 정보 조회", description = "회원의 요약 정보를 조회합니다. - 수정 필요")
    @ApiResponse(responseCode = "200", description = "회원 정보 요약(기부내역 포함)")
    @PostMapping("/summary")
    public ResponseEntity<?> getMemberSummary(Long memberId) {

        var fundingInfo1 = FundingInfoDTO.builder()
                                         .img("http://url ---")
                                         .fundingId(1L)
                                         .fundingAmount(13300)
                                         .ongoing(false)
                                         .build();

        var fundingInfo2 = FundingInfoDTO.builder()
                                         .img("http://url ---")
                                         .fundingId(2L)
                                         .fundingAmount(3300)
                                         .ongoing(false)
                                         .build();

        var fundingInfo3 = FundingInfoDTO.builder()
                                         .img("http://url ---")
                                         .fundingId(3L)
                                         .fundingAmount(100)
                                         .ongoing(true)
                                         .build();

        var res = MemberSummaryResponseDTO.builder()
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

        return response.success(res, HttpStatus.OK);
    }
}
