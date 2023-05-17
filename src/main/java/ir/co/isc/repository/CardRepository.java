package ir.co.isc.repository;


import io.swagger.v3.oas.annotations.Operation;
import ir.co.isc.entity.Cards;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {

    @Query("select c from Cards c where c.cardNumber=:cardNumber")
    Optional<Cards> findCardsByCardNumber(String cardNumber);

    @Query("select c from Cards c where c.issuerCode=:issuerCode")
    Optional<Cards> findBinCardByIssuerCode(String issuerCode);

    @Query("select c.cardType from Cards c where c.issuerCode=:cardType")
    Optional<Cards> findCardType(String cardType);

    @Query("select c from Cards c join Customers cu on cu.id = c.customers where cu.nationalCode=:nationalCode or cu.accountNumber=:accountNumber")
    Optional<Cards> findCardsByNationalCodeOrAccountNumber(String nationalCode, String accountNumber);
}




