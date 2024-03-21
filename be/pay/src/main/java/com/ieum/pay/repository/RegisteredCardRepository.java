package com.ieum.pay.repository;

import com.ieum.pay.domain.RegisteredCards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredCardRepository extends JpaRepository<RegisteredCards,Long> {
}
