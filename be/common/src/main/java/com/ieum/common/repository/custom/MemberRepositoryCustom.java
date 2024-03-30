package com.ieum.common.repository.custom;

import com.ieum.common.domain.Members;
import com.ieum.common.dto.member.res.ProfileResponseDto;
import com.ieum.common.dto.member.res.RecipientResponseDto;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Members> fetchMemberByPhoneNumber(String phoneNumber);

    Optional<ProfileResponseDto> fetchProfileById(Long memberId);

    Optional<RecipientResponseDto> fetchRecipientByPhoneNumber(String phoneNumber);
}
