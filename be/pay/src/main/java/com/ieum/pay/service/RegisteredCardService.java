package com.ieum.pay.service;

import com.ieum.pay.domain.Cards;
import com.ieum.pay.domain.RegisteredCards;
import com.ieum.pay.repository.RegisteredCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisteredCardService {
    private final RegisteredCardRepository registeredCardRepository;

    public Long registerCard(Cards card, Long memberId, String cardNickname) {
        RegisteredCards registeredCard = RegisteredCards.builder()
                .cardId(card.getCardId())
                .cardNickname(cardNickname)
                .cardIssuer(card.getIssuer())
                .memberId(memberId)
                .build();
        RegisteredCards result = registeredCardRepository.save(registeredCard);

        return result.getRegisteredCardId();
    }

    public List<RegisteredCards> getCardList(Long memberId) {
        return registeredCardRepository.findByMemberId(memberId);
    }
}
