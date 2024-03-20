package com.ieum.common.service;

import com.ieum.common.domain.Members;
import com.ieum.common.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public Members registMember(Members member) {
        return memberRepository.save(member);
    }

    public Members getMember(Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다. ID: " + memberId));
    }

    public Members getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public Members getMemberByPhoneNumberAndPassword(String phoneNumber, String password) {
        return memberRepository.findByPhoneNumberAndPassword(phoneNumber, password);
    }

    public boolean updatePassword(Long memberId, String password, String newPassword) {
        try {
            Optional<Members> optionalMember = memberRepository.findById(memberId);
        
            if (!optionalMember.isPresent()) {
                return false;
            }
            Members member = optionalMember.get();
    
            if (!password.equals(member.getPassword())) {
                return false; // 현재 비밀번호가 일치하지 않는 경우
            }
    
            member.setPassword(newPassword);
            memberRepository.save(member);
    
            return true;
            
        } catch (Exception e) { // 변경 실패
            return false;
        }
    }

    public boolean updateNickname(Long memberId, String nickname) {
        try {
            Optional<Members> optionalMember = memberRepository.findById(memberId);
            if (!optionalMember.isPresent()) {
                return false;
            }
            Members member = optionalMember.get();

            member.setNickname(nickname);
            memberRepository.save(member);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
