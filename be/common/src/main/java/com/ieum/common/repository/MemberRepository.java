package com.ieum.common.repository;

import com.ieum.common.domain.Members;
import com.ieum.common.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long>, MemberRepositoryCustom {

    Members findByPhoneNumber(String phoneNumber);

    Members findByPhoneNumberAndPassword(String phoneNumber, String password);

}