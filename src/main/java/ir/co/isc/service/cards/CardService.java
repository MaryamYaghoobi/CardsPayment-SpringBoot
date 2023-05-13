package ir.co.isc.service.cards;


import ir.co.isc.entity.Cards;
import ir.co.isc.entity.Customers;
import ir.co.isc.service.customers.CustomersService;
import ir.co.isc.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import ir.co.isc.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final CustomersService customersService;

    public Cards getById(long id) {
        Optional<Cards> data = cardRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    //add Card
    public ResponseEntity<Cards> create(Cards card) {
        Customers customers = new Customers();
        card.setCardStatus(card.getCardStatus());
        card.setCardType(card.getCardType());
        card.setCardNumber();
        card.setCvv2();
        card.setIssuerName(card.getIssuerName());
        card.setIssuerCode(card.getIssuerCode());
        card.setExpirationDate(card.getExpirationDate());
        card.setCustomers(customersService.findCustomersByNationalCode(customers.getNationalCode()));
        Cards savedCard = cardRepository.save(card);
        return new ResponseEntity(savedCard, HttpStatus.CREATED);
    }

    //ok
    public ResponseEntity<Cards> findCardsByCardNumber(String cardNumber) {

        Optional<Cards> foundedCard = cardRepository.findCardsByCardNumber(cardNumber);

        if (foundedCard.isPresent()) {

            return new ResponseEntity(foundedCard, HttpStatus.OK);

        } else {

            throw new ir.co.isc.exception.EntityNotFoundException(Constant.CARD_NOT_FOUND_ERROR_MESSAGE);

        }

    }
    public ResponseEntity<Cards> findCardsByNationalCodeOrAccountNumber(String nationalCode,String accountNumber) {

        Optional<Cards> foundedCard = cardRepository.findCardsByNationalCodeOrAccountNumber(nationalCode,accountNumber);

        if (foundedCard.isPresent()) {

            return new ResponseEntity(foundedCard, HttpStatus.OK);

        } else {

            throw new ir.co.isc.exception.EntityNotFoundException(Constant.CARD_NOT_FOUND_ERROR_MESSAGE);

        }

    }
}

