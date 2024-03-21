package com.ieum.funding.repository;

import com.ieum.funding.dto.Facilities;
import com.ieum.funding.dto.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityTypeRepository extends JpaRepository<FacilityType, Long> {

}
