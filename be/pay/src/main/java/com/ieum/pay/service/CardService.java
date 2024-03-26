package com.ieum.pay.service;

import com.ieum.pay.domain.Cards;
import com.ieum.pay.domain.RegisteredCards;
import com.ieum.pay.repository.CardRepository;
import com.ieum.pay.repository.RegisteredCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final RegisteredCardRepository registeredCardRepository;

    public Cards findCard(String cardNumber){
        int cardNumFour = Integer.parseInt(cardNumber.substring(0, 4));
        int cardNumSix = Integer.parseInt(cardNumber.substring(0,6));
        List<Cards> cardsList = cardRepository.findByCardNumber(cardNumFour, cardNumSix);

        Cards cards = new Cards(4000L,"이음페이","이음카드",0,0);

        for (Cards c : cardsList) {
            int bin = c.getCardBin();
            if(bin < 10000){
                return c;
            }
            if(cardNumSix == bin){
                return c;
            }
        }
        return cards;
    }

    public boolean delete(Long memberId, Long id) {
        RegisteredCards card = registeredCardRepository.findByMemberIdAndRegisteredCardId(memberId,id);
        if(card == null)
            return false;
        registeredCardRepository.deleteById(id);
        return true;
    }
}