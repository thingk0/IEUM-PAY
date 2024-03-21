package com.ieum.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 IDENTITY로 지정
    private Long memberId;

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'GR001'")
    @Builder.Default
    private String gradeCode = "GR001";

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    @Builder.Default
    private Long paycardId = 0L;

    private String phoneNumber;
    private String password;
    private String name;
    private String nickname;
}
