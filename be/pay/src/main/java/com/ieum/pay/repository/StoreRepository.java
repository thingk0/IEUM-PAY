package com.ieum.pay.repository;

import com.ieum.pay.domain.Stores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Stores, Long> {

}
