package com.ieum.common.service;

import com.ieum.common.domain.Members;
import com.ieum.common.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Members saveMember(Members member) {
        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public Members getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. ID: " + memberId));
    }

    @Transactional(readOnly = true)
    public Members getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }
}
