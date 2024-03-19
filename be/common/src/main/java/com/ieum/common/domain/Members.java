package com.ieum.common.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 IDENTITY로 지정
    private Long memberId;
    private String gradeCode;
    private Long paycardId;
    private String phoneNumber;
    private String password;
    private String name;
    private String nickname;

}
