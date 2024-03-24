package com.ieum.common.service;

import com.ieum.common.domain.Members;
import com.ieum.common.dto.member.SignupRequestDto;
import com.ieum.common.exception.PayMoneyCreationFailedException;
import com.ieum.common.exception.member.ExistingPhoneNumberException;
import com.ieum.common.exception.member.MemberNotFoundException;
import com.ieum.common.exception.member.PasswordMismatchException;
import com.ieum.common.feign.PayFeignClient;
import com.ieum.common.repository.GradeRepository;
import com.ieum.common.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final PayFeignClient payFeignClient;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final GradeRepository gradeRepository;

    public Long signup(SignupRequestDto request) {
        validatePasswordConfirmation(request.getPassword(), request.getPasswordConfirm());
        validatePhoneNumberDuplication(request.getPhoneNumber());

        Members savedMember = memberRepository.save(Members.of(request.getPhoneNumber(),
                                                         request.getName(),
                                                         request.getNickname(),
                                                         passwordEncoder.encode(request.getPassword()),
                                                         gradeRepository.findByCode("GR001")));

        if (!payFeignClient.createPayMoney(savedMember.getId())) {
            throw new PayMoneyCreationFailedException();
        }

        return savedMember.getId();
    }

    private void validatePasswordConfirmation(String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException();
        }
    }

    private void validatePhoneNumberDuplication(String phoneNumber) {
        memberRepository.fetchMemberByPhoneNumber(phoneNumber)
                        .ifPresent(m -> {
                            throw new ExistingPhoneNumberException();
                        });
    }


    public Members registMember(Members member) {
        return memberRepository.save(member);
    }

    public Members getMember(Long memberId) {
        return memberRepository.findById(memberId)
                               .orElseThrow(MemberNotFoundException::new);
    }

    public Members getMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public Members getMemberByPhoneNumberAndPassword(String phoneNumber, String password) {
        return memberRepository.findByPhoneNumberAndPassword(phoneNumber, password);
    }


    public String checkPaymentPassword(Long memberId, String paymentPassword) {
        return "";
    }


    public Long delete(Long memberId) {
        memberRepository.deleteById(memberId);
        return memberId;
    }
}
