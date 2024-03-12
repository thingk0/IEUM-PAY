package com.ieum.common.controller;

import com.ieum.common.request.FundingDonationRequestDTO;
import com.ieum.common.request.FundingLinkupRequestDTO;
import com.ieum.common.request.FundingUnlinkRequestDTO;
import com.ieum.common.response.FundingCompleteDetailResponseDTO;
import com.ieum.common.response.FundingCompleteInfoResponseDTO;
import com.ieum.common.response.FundingDonationResponseDTO;
import com.ieum.common.response.FundingInfoResponseDTO;
import com.ieum.common.response.FundingMemberDTO;
import com.ieum.common.response.FundingOngoingDetailResponseDTO;
import com.ieum.common.response.FundingOngoingInfoResponseDTO;
import com.ieum.common.response.FundingResultResponseDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funding")
public class FundingController {

    @GetMapping("/{fundingId}/complete")
    public ResponseEntity<FundingCompleteDetailResponseDTO> getFundingCompleteDetail(
        @PathVariable Long fundingId) {

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

        FundingCompleteDetailResponseDTO response = FundingCompleteDetailResponseDTO.builder()
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
            .fundingMemberList(Arrays.asList(fundingMember1, fundingMember2, fundingMember3))
            .content(content) // MongoDB의 Document를 사용하는 경우
            .build();

        return ResponseEntity.ok(response); // 생성된 DTO 반환

    }

    @GetMapping("/{fundingId}/ongoing")
    public ResponseEntity<FundingOngoingDetailResponseDTO> getFundingOngoingDetail(
        @PathVariable Long fundingId) {

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

        FundingOngoingDetailResponseDTO response = FundingOngoingDetailResponseDTO.builder()
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
            .fundingMemberList(Arrays.asList(fundingMember1, fundingMember2, fundingMember3))
            .content(content) // MongoDB의 Document를 사용하는 경우
            .build();

        return ResponseEntity.ok(response); // 생성된 DTO 반환

    }

    @PostMapping("/donation")
    public ResponseEntity<FundingDonationResponseDTO> donationDirectly(
        @RequestBody FundingDonationRequestDTO request) {
        FundingDonationResponseDTO response = FundingDonationResponseDTO.builder()
            .fundingId(1L)
            .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/donation/result/{fundingId}")
    public ResponseEntity<FundingResultResponseDTO> getFundgingResult(
        @PathVariable Long fundingId) {
        FundingResultResponseDTO response = FundingResultResponseDTO.builder()
            .fundingTitle("떡잎어린이집 후원")
            .factilityName("떡잎어린이집")
            .facilityImage(
                "https://ko.wikipedia.org/wiki/%EC%8B%A0%EC%A7%B1%EA%B5%AC#/media/%ED%8C%8C%EC%9D%BC:%EC%8B%A0%EC%A7%B1%EA%B5%AC.png")
            .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/info/{fundingId}/{amount}")
    public ResponseEntity<FundingInfoResponseDTO> getFundgingInfo(
        @PathVariable Long fundingId, @PathVariable int amount) {
        FundingInfoResponseDTO response = FundingInfoResponseDTO.builder()
            .fundingId(fundingId)
            .amount(amount)
            .facilityName("떡잎어린이집")
            .paymoneyAmount(400)
            .chargeAmount(10000)
            .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/linkup")
    public ResponseEntity<HttpStatus> fundingLinkup(@RequestBody FundingLinkupRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list/complete")
    public ResponseEntity<List<FundingCompleteInfoResponseDTO>> getFundingCompleteList () {
        List<FundingCompleteInfoResponseDTO> response = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            FundingCompleteInfoResponseDTO completeInfo1 = FundingCompleteInfoResponseDTO.builder()
                .fundingId(1L)
                .facilityName("btc")
                .fundingTitle("과자를 사주세요")
                .fundingOpenDate(sdf.parse("2023-02-02"))
                .fundingFinishDate(sdf.parse("2023-03-02"))
                .facilityImage("http:FDSAFSDAFDSAF")
                .fundingPeopleCnt(10)
                .goalAmount(500000)
                .build();

            FundingCompleteInfoResponseDTO completeInfo2 = FundingCompleteInfoResponseDTO.builder()
                .fundingId(2L)
                .facilityName("btc2")
                .fundingTitle("과자를 사주세요2")
                .fundingOpenDate(sdf.parse("2023-02-02"))
                .fundingFinishDate(sdf.parse("2023-03-02"))
                .facilityImage("http:FDSAFSDAFDSAF")
                .fundingPeopleCnt(10)
                .goalAmount(500000)
                .build();

            response.add(completeInfo1);
            response.add(completeInfo2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/complete")
    public ResponseEntity<List<FundingOngoingInfoResponseDTO>> getFundingOngoingList () {
        List<FundingOngoingInfoResponseDTO> response = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            FundingOngoingInfoResponseDTO completeInfo1 = FundingOngoingInfoResponseDTO.builder()
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

            FundingOngoingInfoResponseDTO completeInfo2 = FundingOngoingInfoResponseDTO.builder()
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

            response.add(completeInfo1);
            response.add(completeInfo2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/unlink")
    public ResponseEntity<HttpStatus> fundingUnlink(@RequestBody FundingUnlinkRequestDTO request) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
