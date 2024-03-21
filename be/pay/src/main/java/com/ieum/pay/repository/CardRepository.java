package com.ieum.pay.repository;

import com.ieum.pay.domain.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Cards, Long> {

    @Query("SELECT c FROM Cards c " +
            " WHERE c.cardBin = :number1 or c.cardBin = :number2 order by c.cardBin asc ")
    List<Cards> findByCardNumber(int number1, int number2);
}
