package com.ieum.common.controller;

import com.ieum.common.dto.request.FundingDonationRequestDTO;
import com.ieum.common.dto.request.FundingLinkupRequestDTO;
import com.ieum.common.dto.request.FundingUnlinkRequestDTO;
import com.ieum.common.dto.response.FundingCompleteDetailResponseDTO;
import com.ieum.common.dto.response.FundingCompleteInfoResponseDTO;
import com.ieum.common.dto.response.FundingDonationResponseDTO;
import com.ieum.common.dto.response.FundingInfoResponseDTO;
import com.ieum.common.dto.FundingMemberDTO;
import com.ieum.common.dto.response.FundingOngoingDetailResponseDTO;
import com.ieum.common.dto.response.FundingOngoingInfoResponseDTO;
import com.ieum.common.dto.response.FundingResultResponseDTO;
import com.ieum.common.format.response.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "funding", description = "Funding API - 목업")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/funding")
public class FundingController {

    private final ResponseTemplate response;


    @Operation(summary = "완료된 펀딩 상세 조회", description = "완료된 펀딩의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "완료된 펀딩 상세 정보 조회 성공")
    @GetMapping("/{fundingId}/complete")
    public ResponseEntity<?> getFundingCompleteDetail(@PathVariable("fundingId") Long fundingId) {

        FundingMemberDTO fundingMember1 = FundingMemberDTO.builder()
            .nickname("abc")
            .amount(100000)
            .build();

        FundingMemberDTO fundingMember2 = FundingMemberDTO.builder()
            .nickname("qqq")
            .amount(200000)
            .build();

        FundingMemberDTO fundingMember3 = FundingMemberDTO.builder()
            .nickname("yyy")
            .amount(300000)
            .build();

        Document content = new Document()
            .append("title", "Sample Project")
            .append("description", "This is a sample MongoDB document")
            .append("status", "active")
            .append("budget", 5000)
            .append("participants", Arrays.asList("John Doe", "Jane Doe", "Steve Smith"))
            .append("startDate", "2024-01-01")
            .append("endDate", "2024-12-31")
            .append("notes",
                new Document("important", "Remember to update the document regularly"));

        var res = FundingCompleteDetailResponseDTO.builder()
            .facilityName("btc")
            .facilityAddress("대전광역시 유성구 oo oo oo")
            .facilityPhoneNumber("042-000-0000")
            .facilityRepresentativeName("홍길동")
            .facilityRepresentativePhoneNumber("010-0123-4567")
            .facilityCapacity(10)
            .facilityImage("http://example.com/image")
            .fundingOpenDate("2023-02-02")
            .fundingPeopleCnt(3)
            .fundingTitle("helpMe")
            .goalAmount(500000)
            .fundingMemberList(
                Arrays.asList(fundingMember1, fundingMember2, fundingMember3))
            .content(content) // MongoDB의 Document를 사용하는 경우
            .build();

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "진행 중인 펀딩 상세 조회", description = "진행 중인 펀딩의 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "진행 중인 펀딩 상세 정보 조회 성공")
    @GetMapping("/{fundingId}/ongoing")
    public ResponseEntity<?> getFundingOngoingDetail(@PathVariable("fundingId") Long fundingId) {

        FundingMemberDTO fundingMember1 = FundingMemberDTO.builder()
            .nickname("abc")
            .amount(100000)
            .build();

        FundingMemberDTO fundingMember2 = FundingMemberDTO.builder()
            .nickname("qqq")
            .amount(200000)
            .build();

        FundingMemberDTO fundingMember3 = FundingMemberDTO.builder()
            .nickname("yyy")
            .amount(300000)
            .build();

        Document content = new Document()
            .append("title", "Sample Project")
            .append("description", "This is a sample MongoDB document")
            .append("status", "active")
            .append("budget", 5000)
            .append("participants", Arrays.asList("John Doe", "Jane Doe", "Steve Smith"))
            .append("startDate", "2024-01-01")
            .append("endDate", "2024-12-31")
            .append("notes",
                new Document("important", "Remember to update the document regularly"));

        var res = FundingOngoingDetailResponseDTO.builder()
            .facilityName("btc")
            .facilityAddress("대전광역시 유성구 oo oo oo")
            .facilityPhoneNumber("042-000-0000")
            .facilityRepresentativeName("홍길동")
            .facilityRepresentativePhoneNumber("010-0123-4567")
            .facilityCapacity(10)
            .facilityImage("http://example.com/image")
            .fundingOpenDate("2023-02-02")
            .fundingPeopleCnt(3)
            .fundingTitle("helpMe")
            .goalAmount(500000)
            .currentAmount(4000)
            .fundingMemberList(
                Arrays.asList(fundingMember1, fundingMember2, fundingMember3))
            .content(content) // MongoDB의 Document를 사용하는 경우
            .build();

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 기부", description = "펀딩에 직접 기부합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 기부 성공 - 펀딩ID 반환")
    @PostMapping("/donation")
    public ResponseEntity<?> donationDirectly(@RequestBody FundingDonationRequestDTO request) {
        var res = FundingDonationResponseDTO.builder()
            .fundingId(1L)
            .build();
        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 결과 조회(직접 기부)", description = "직접기부 시 펀딩의 결과를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 결과 조회 성공")
    @GetMapping("/donation/result/{fundingId}")
    public ResponseEntity<?> getFundgingResult(
        @PathVariable("fundingId") Long fundingId) {
        var res = FundingResultResponseDTO.builder()
            .fundingTitle("떡잎어린이집 후원")
            .factilityName("떡잎어린이집")
            .facilityImage(
                "https://ko.wikipedia.org/wiki/%EC%8B%A0%EC%A7%B1%EA%B5%AC#/media/%ED%8C%8C%EC%9D%BC:%EC%8B%A0%EC%A7%B1%EA%B5%AC.png")
            .build();

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "직접기부 결제 정보 요청", description = "직접기부 결제시 해당 결제에 대한 정보 요청")
    @ApiResponse(responseCode = "200", description = "정보 조회 성공")
    @GetMapping("/info/{fundingId}/{amount}")
    public ResponseEntity<?> getFundgingInfo(@PathVariable("fundingId") Long fundingId, @PathVariable("amount") int amount) {
        var res = FundingInfoResponseDTO.builder()
            .fundingId(fundingId)
            .amount(amount)
            .facilityName("떡잎어린이집")
            .paymoneyAmount(400)
            .chargeAmount(10000)
            .build();

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "펀딩 연계", description = "사용자를 특정 펀딩에 연계시킵니다.")
    @ApiResponse(responseCode = "200", description = "펀딩 연계 성공")
    @PostMapping("/linkup")
    public ResponseEntity<?> fundingLinkup(@RequestBody FundingLinkupRequestDTO request) {
        return response.success(HttpStatus.OK);
    }

    @Operation(summary = "완료된 펀딩 목록 조회", description = "완료된 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "완료된 펀딩 목록 조회 성공")
    @GetMapping("/list/complete")
    public ResponseEntity<?> getFundingCompleteList() {
        List<FundingCompleteInfoResponseDTO> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            var completeInfo1 = FundingCompleteInfoResponseDTO.builder()
                .fundingId(1L)
                .facilityName("btc")
                .fundingTitle("과자를 사주세요")
                .fundingOpenDate(sdf.parse("2023-02-02"))
                .fundingFinishDate(sdf.parse("2023-03-02"))
                .facilityImage("http:FDSAFSDAFDSAF")
                .fundingPeopleCnt(10)
                .goalAmount(500000)
                .build();

            var completeInfo2 = FundingCompleteInfoResponseDTO.builder()
                .fundingId(2L)
                .facilityName("btc2")
                .fundingTitle("과자를 사주세요2")
                .fundingOpenDate(sdf.parse("2023-02-02"))
                .fundingFinishDate(sdf.parse("2023-03-02"))
                .facilityImage("http:FDSAFSDAFDSAF")
                .fundingPeopleCnt(10)
                .goalAmount(500000)
                .build();

            res.add(completeInfo1);
            res.add(completeInfo2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.success(res, HttpStatus.OK);
    }

    @Operation(summary = "진행 중인 펀딩 목록 조회", description = "현재 진행 중인 모든 펀딩의 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "진행 중인 펀딩 목록 조회 성공")
    @GetMapping("/list/ongoing")
    public ResponseEntity<?> getFundingOngoingList() {
        List<FundingOngoingInfoResponseDTO> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            var completeInfo1 = FundingOngoingInfoResponseDTO.builder()
                .fundingId(1L)
                .facilityName("btc")
                .fundingTitle("과자를 사주세요")
                .fundingOpenDate(sdf.parse("2023-02-02"))
                .fundingFinishDate(sdf.parse("2023-03-02"))
                .facilityImage("http:FDSAFSDAFDSAF")
                .fundingPeopleCnt(10)
                .goalAmount(500000)
                .currentAmount(1000)
                .build();

            var completeInfo2 = FundingOngoingInfoResponseDTO.builder()
                .fundingId(2L)
                .facilityName("btc2")
                .fundingTitle("과자를 사주세요2")
                .fundingOpenDate(sdf.parse("2023-02-02"))
                .fundingFinishDate(sdf.parse("2023-03-02"))
                .facilityImage("http:FDSAFSDAFDSAF")
                .fundingPeopleCnt(10)
                .goalAmount(500000)
                .currentAmount(1000)
                .build();

            res.add(completeInfo1);
            res.add(completeInfo2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response.success(res, HttpStatus.OK);
    }

    @PostMapping("/unlink")
    public ResponseEntity<?> fundingUnlink(@RequestBody FundingUnlinkRequestDTO request) {
        return response.success(HttpStatus.OK);
    }
}
