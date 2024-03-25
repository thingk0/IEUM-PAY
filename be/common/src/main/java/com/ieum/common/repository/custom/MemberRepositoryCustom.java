package com.ieum.common.repository.custom;

import com.ieum.common.domain.Members;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Members> fetchMemberByPhoneNumber(String phoneNumber);
}
