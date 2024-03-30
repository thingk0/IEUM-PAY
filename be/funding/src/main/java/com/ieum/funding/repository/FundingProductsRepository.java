package com.ieum.funding.repository;

import com.ieum.funding.domain.FundingProducts;
import com.ieum.funding.dto.FundingProductDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FundingProductsRepository extends JpaRepository<FundingProducts, Long> {

    @Query("SELECT new com.ieum.funding.dto.FundingProductDTO(" +
        "fp.fundingProductQuantity, " +
        "fp.fundingProductPrice, " +
        "sp.productName, " +
        "sp.productImage, " +
        "sp.price, " +
        "s.sponsorName )" +
        "FROM FundingProducts fp " +
        "JOIN SponsorProducts sp ON fp.sponsorProductId = sp.sponsorProductId " +
        "JOIN Sponsors s ON sp.sponsorTypeCode = s.sponsorTypeCode " +
        "WHERE fp.fundingId = :fundingId")
    List<FundingProductDTO> findFundingProductDTOByFundingId(@Param("fundingId") Long fundingId);

    List<FundingProducts> findByFundingId(Long fundingId);
}
