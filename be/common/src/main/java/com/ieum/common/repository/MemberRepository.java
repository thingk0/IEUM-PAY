package com.ieum.common.repository;

import com.ieum.common.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Members findByPhoneNumber(String phoneNumber);
    Members findByPhoneNumberAndPassword(String phoneNumber, String password);
    // 여기에 추가적인 쿼리 메소드를 정의할 수 있습니다.
}