package com.ieum.funding.repository;

import com.ieum.funding.domain.SponsorProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SponsorProductsRepository extends JpaRepository<SponsorProducts, String> {

    SponsorProducts findFirstBySponsorProductId(Long sponsorProductId);
}
