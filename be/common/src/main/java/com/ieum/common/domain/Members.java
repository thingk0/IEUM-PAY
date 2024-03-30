package com.ieum.common.domain;

import com.ieum.common.domain.base.BaseEntity;
import com.ieum.common.exception.member.DuplicateNicknameChangeException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members", indexes = {
    @Index(name = "members_phone_number_unq_index", columnList = "phone_number"),
})
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE members SET is_deleted = TRUE WHERE member_id = ?")
public class Members extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_code", nullable = false)
    private Grade gradeCode;

    @Column(name = "paycard_id")
    private Long paycardId;

    @Column(name = "phone_number", nullable = false, unique = true, columnDefinition = "CHAR(11)")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;


    public static Members of(String phoneNumber, String name, String nickname, String encodedPassword, Grade gradeCode) {
        return Members.builder()
                      .gradeCode(gradeCode)
                      .phoneNumber(phoneNumber)
                      .password(encodedPassword)
                      .name(name)
                      .nickname(nickname)
                      .build();
    }


    public String updateNickname(String newNickname) {
        String prevNickname = this.nickname;
        if (prevNickname.equals(newNickname)) {
            throw new DuplicateNicknameChangeException();
        }
        this.nickname = newNickname;
        return prevNickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updatePaycardId(Long paycardId){this.paycardId = paycardId;}
}
