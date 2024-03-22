package com.ieum.funding.repository;

import com.ieum.funding.domain.FundingMembers;
import com.ieum.funding.dto.FundingMemberDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingMembersRepository extends JpaRepository<FundingMembers,Long> {

    List<FundingMemberDTO> findByFundingId(Long fundingId);
    Optional<FundingMembers> findFirstByFundingIdAndMemberId(Long fundingId, Long memberId);
}
