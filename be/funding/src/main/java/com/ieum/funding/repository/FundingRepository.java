package com.ieum.funding.repository;

import com.ieum.funding.domain.Funding;
import com.ieum.funding.response.FundingInfoResponseDTO;
import com.ieum.funding.dto.FundingDetailBaseDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface FundingRepository extends JpaRepository<Funding, Long> {

    String BASE_QUERY_INFO_LIST = "SELECT new com.ieum.funding.response.FundingInfoResponseDTO(" +
        "f.fundingId, " +
        "fac.facilityName, " +
        "f.fundingTitle, " +
        "f.fundingOpenDate, " +
        "f.fundingFinishDate, " +
        "fac.facilityImage, " +
        "(SELECT COUNT(fm) as cnt FROM FundingMembers fm WHERE fm.fundingId = f.fundingId), " +
        "f.goalAmount, " +
        "f.currentAmount) " +
        "FROM Funding f " +
        "LEFT JOIN Facilities fac ON fac.facilityId = f.facilityId ";

    @Query(BASE_QUERY_INFO_LIST + "WHERE f.fundingFinishDate IS NULL")
    List<FundingInfoResponseDTO> findOngoingFundingList();

    @Query(BASE_QUERY_INFO_LIST + "WHERE f.fundingFinishDate IS NOT NULL")
    List<FundingInfoResponseDTO> findCompleteFundingList();
    @Query ("SELECT new com.ieum.funding.dto.FundingDetailBaseDTO(" +
        "f.fundingId, " +
        "fac.facilityName, " +
        "fac.facilityAddress, " +
        "fac.facilityPhoneNumber, " +
        "fac.facilityRepresentativeName, " +
        "fac.facilityRepresentativePhoneNumber, " +
        "fac.facilityCapacity, " +
        "fac.facilityImage, " +
        "f.fundingOpenDate, " +
        "(SELECT COUNT(fm) as cnt FROM FundingMembers fm WHERE fm.fundingId = f.fundingId), " +
        "f.fundingTitle, " +
        "f.goalAmount, " +
        "f.currentAmount," +
        "f.fundingContent) " +
        "FROM Funding f " +
        "LEFT JOIN Facilities fac ON fac.facilityId = f.facilityId " +
        "WHERE f.fundingId = :fundingId")
    FundingDetailBaseDTO findFundingDetail(@Param("fundingId") Long fundingId);
}
