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
    //جستجوی کارت با شماره کارت
    //search
    @Query("select c from Cards c where c.cardNumber=:cardNumber")
    Optional<Cards> findCardsByCardNumber(String cardNumber);

   /* categoryElement
   //findAll CardDao OK search
    @Query("select c from Cards c where c.cardType.code =:code ")
    List<Cards> findCardsByCardType();

    //findAll CardDao OK
    @Query("select c from Cards c where c.cardStatus.code =:code ")
    List<Cards> findActiveCards();*/

    //validation Card

    @Query("select c.issuerName from Cards c where c.issuerCode=:issuerCode")
    Optional<Cards> findBinCardByIssuerCode(String issuerCode);

    @Query("select c.cardType from Cards c where c.issuerCode=:issuerCode")
    Optional<Cards> findCardTypeByIssuerCode(String issuerCode);

    @Query("select c from Cards c join Customers cu on c.customers= cu.id where cu.nationalCode=:nationalCode or cu.accountNumber=:accountNumber")

    Optional<Cards> findCardsByNationalCodeOrAccountNumber(String nationalCode,String accountNumber);
}




