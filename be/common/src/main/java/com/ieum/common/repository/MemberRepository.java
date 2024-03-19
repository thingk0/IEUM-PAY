package com.ieum.common.repository;

import com.ieum.common.domain.maria.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByPhoneNumber(String phoneNumber);
    // 여기에 추가적인 쿼리 메소드를 정의할 수 있습니다.
}