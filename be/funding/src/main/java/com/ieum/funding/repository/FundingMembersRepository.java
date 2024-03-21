package com.ieum.funding.repository;

import com.ieum.funding.dto.FundingMembers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingMembersRepository extends JpaRepository<FundingMembers,Long> {

}
