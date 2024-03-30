package com.ieum.common.service;

import com.ieum.common.domain.Grade;
import com.ieum.common.repository.GradeRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    @Transactional(readOnly = true)
    public Grade getGradeByCode(String gradeCode) {
        return gradeRepository.findById(gradeCode)
                              .orElseThrow(() -> new EntityNotFoundException("Grade not found with code: " + gradeCode));
    }

}
