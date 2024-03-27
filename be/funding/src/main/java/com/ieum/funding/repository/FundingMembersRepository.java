package com.ieum.funding.repository;

import com.ieum.funding.domain.FundingMembers;
import com.ieum.funding.dto.FundingMemberDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FundingMembersRepository extends JpaRepository<FundingMembers, Long> {

    @Query("SELECT new com.ieum.funding.dto.FundingMemberDTO(fm.nickname, fm.memberId, fm.fundingTotalAmount) " +
        "FROM FundingMembers fm WHERE fm.fundingId = :fundingId")
    List<FundingMemberDTO> findByFundingId(Long fundingId);

    Optional<FundingMembers> findFirstByFundingIdAndMemberId(Long fundingId, Long memberId);

    @Modifying
    @Query("UPDATE FundingMembers f SET f.autoFundingStatus = false WHERE f.memberId = :memberId AND f.fundingId = :fundingId")
    void unlink(Long fundingId, Long memberId);

    @Modifying
    @Query("UPDATE FundingMembers f SET f.autoFundingStatus = false WHERE f.memberId = :memberId")
    void unlinkAll(Long memberId);

    @Modifying
    @Query("UPDATE FundingMembers f SET f.autoFundingStatus = true WHERE f.memberId = :memberId AND f.fundingId = :fundingId")
    void linkup(Long fundingId, Long memberId);

    @Modifying
    @Transactional
    @Query("UPDATE FundingMembers fm SET fm.fundingTotalAmount = fm.fundingTotalAmount + :amount WHERE fm.fundingId = :fundingId AND fm.memberId = :memberId")
    void updateFundingMember(Long fundingId, Long memberId, Integer amount);

    @Modifying
    @Query("UPDATE FundingMembers f SET f.autoFundingStatus = false WHERE f.fundingId = :fundingId")
    void unlinkAllByFundingId(Long fundingId);
}
