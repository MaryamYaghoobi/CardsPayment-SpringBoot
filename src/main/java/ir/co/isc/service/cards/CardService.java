package ir.co.isc.service.cards;


import ir.co.isc.entity.Cards;
import ir.co.isc.entity.Customers;
import ir.co.isc.service.customers.CustomersService;
import ir.co.isc.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import ir.co.isc.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardService {
    Logger logger = LogManager.getLogger();

    private final CardRepository cardRepository;
    private final CustomersService customersService;
    private final CardService cardService;

    public Cards getById(long id) {
        Optional<Cards> data = cardRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }
    public Cards findBinCardByIssuerCode(String issuerCode) {
        Optional<Cards> data = cardRepository.findBinCardByIssuerCode(issuerCode);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Cards findCardType(String cardType) {
        Optional<Cards> data = cardRepository.findCardType(cardType);
        if (data.isPresent()) return data.get();
        return null;
    }

    public String generateCardNumber(String issuerCode) {
        StringBuilder CardNumber = new StringBuilder(String.valueOf(issuerCode));
        CardNumber.append(cardService.findBinCardByIssuerCode(issuerCode));
        int checkDigit = this.getCheckDigit(CardNumber.toString());
        CardNumber.append(checkDigit);
        return CardNumber.toString();
    }

    public int getCheckDigit(String cardNumber) {
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit = Integer.parseInt(cardNumber.substring(i, (i + 1)));
            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }
        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }

    public String generateCVV2() {
        int cvv2 = 000;
        Random rand = new Random();
        for (int i = 1; i <= 100; i++) {
            int randomNum = rand.nextInt((999 - 100) + 1) + 100;

            logger.info("has been created : " + randomNum);

        }
        return String.valueOf(cvv2);
    }

    public ResponseEntity<Cards> createNewCard(Cards card) {
        Cards newCard = new Cards();
        Customers customer = new Customers();
        if (customersService.checkDuplicateNationalCode(customer.getNationalCode())) {
            if (cardService.findBinCardByIssuerCode(card.getIssuerCode()) != null) ;
            {
                if (cardService.findCardType(String.valueOf(card.getCardType())) != null)
                    logger.info("The card is already registered");
            }
        } else
            newCard.setCardStatus(card.getCardStatus());
        newCard.setCardType(card.getCardType());
        newCard.setCardNumber(cardService.generateCardNumber(card.getIssuerCode()));
        newCard.setCvv2(cardService.generateCVV2());
        newCard.setIssuerName(card.getIssuerName());
        newCard.setIssuerCode(card.getIssuerCode());
        newCard.setExpirationDate(card.getExpirationDate());
        Cards savedCard = cardRepository.save(newCard);
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

    public ResponseEntity<Cards> findCardsByNationalCodeOrAccountNumber(String nationalCode, String accountNumber) {

        Optional<Cards> foundedCard = cardRepository.findCardsByNationalCodeOrAccountNumber(nationalCode, accountNumber);

        if (foundedCard.isPresent()) {

            return new ResponseEntity(foundedCard, HttpStatus.OK);

        } else {

            throw new ir.co.isc.exception.EntityNotFoundException(Constant.CARD_NOT_FOUND_ERROR_MESSAGE);

        }

    }
}

