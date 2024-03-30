package com.ieum.pay.repository;

import com.ieum.pay.domain.Cards;
import com.ieum.pay.domain.RegisteredCards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisteredCardRepository extends JpaRepository<RegisteredCards,Long> {
    List<RegisteredCards> findByMemberId(Long memberId);

    RegisteredCards findByMemberIdAndRegisteredCardId(Long memberId, Long registeredCardId);

    RegisteredCards findByRegisteredCardId(Long id);
}
