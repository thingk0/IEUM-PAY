package com.ieum.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long registeredCardId;
    Long cardId;
    String cardNickname;
    Long memberId;
    String cardIssuer;
}
