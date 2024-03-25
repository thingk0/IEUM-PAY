package com.ieum.common.repository.impl;

import static com.ieum.common.domain.QMembers.members;

import com.ieum.common.domain.Members;
import com.ieum.common.repository.custom.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Optional<Members> fetchMemberByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(query.selectFrom(members)
                                        .where(members.phoneNumber.eq(phoneNumber))
                                        .fetchFirst());
    }
}
