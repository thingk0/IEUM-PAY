package com.ieum.common.repository.impl;

import static com.ieum.common.domain.QGrade.grade;
import static com.ieum.common.domain.QMembers.members;

import com.ieum.common.domain.Members;
import com.ieum.common.dto.member.res.ProfileResponseDto;
import com.ieum.common.dto.member.res.RecipientResponseDto;
import com.ieum.common.repository.custom.MemberRepositoryCustom;
import com.querydsl.core.types.Projections;
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

    @Override
    public Optional<ProfileResponseDto> fetchProfileById(Long memberId) {
        return Optional.ofNullable(query.select(
                                            Projections.constructor(ProfileResponseDto.class,
                                                                    members.id,
                                                                    members.name,
                                                                    members.nickname,
                                                                    grade.code,
                                                                    grade.name,
                                                                    members.phoneNumber))
                                        .from(members)
                                        .leftJoin(members.gradeCode, grade)
                                        .where(members.id.eq(memberId))
                                        .fetchFirst());
    }

    @Override
    public Optional<RecipientResponseDto> fetchRecipientByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(query.select(
                                            Projections.constructor(RecipientResponseDto.class,
                                                                    members.id,
                                                                    members.name,
                                                                    members.phoneNumber))
                                        .from(members)
                                        .where(members.phoneNumber.eq(phoneNumber))
                                        .fetchFirst());
    }
}
