package com.ieum.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Grade {

    @Id
    @Column(name = "grade_code")
    private String code;

    @Column(name = "grade_name", nullable = false)
    private String name;

    @Column(name = "grade_member_count", nullable = false)
    private int count;

}
