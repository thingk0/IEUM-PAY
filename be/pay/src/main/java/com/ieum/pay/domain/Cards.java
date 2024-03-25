package com.ieum.pay.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cards {
    @Id
    Long cardId;
    String issuer;
    String cardProduct;
    int isCorporate;
    int cardBin;
}
