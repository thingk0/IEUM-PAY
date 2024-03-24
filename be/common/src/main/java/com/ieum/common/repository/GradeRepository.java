package com.ieum.common.repository;

import com.ieum.common.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, String> {

    Grade findByCode(String gradeCode);
}
