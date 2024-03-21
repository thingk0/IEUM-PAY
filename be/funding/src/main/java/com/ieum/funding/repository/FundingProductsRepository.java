package com.ieum.funding.repository;

import com.ieum.funding.dto.FundingProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingProductsRepository extends JpaRepository<FundingProducts, Long> {

}
