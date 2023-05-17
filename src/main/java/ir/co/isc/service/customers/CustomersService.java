package ir.co.isc.service.customers;

import ir.co.isc.entity.Cards;
import ir.co.isc.entity.Customers;
import ir.co.isc.repository.CustomersRepository;
import ir.co.isc.service.cards.CardService;
import ir.co.isc.util.Constant;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomersService {
    Logger logger = LogManager.getLogger();
    private final CustomersRepository customersRepository;
    private final CardService cardService;

    public ResponseEntity<Customers> addCard(Customers customer, Cards cards) {
        Customers foundedCustomer = customersRepository.findCustomersByNationalCode(customer.getNationalCode());
        foundedCustomer.setCard((Set<Cards>) cardService.createNewCard(cards));
        Customers saveCustomer = customersRepository.save(foundedCustomer);
        return new ResponseEntity(Optional.of(saveCustomer), HttpStatus.CREATED);
    }

    public boolean checkDuplicateNationalCode(String nationalCodeEntered) {
        boolean invalidNationalCode = false;

        List<String> allSavedNationalCodes = customersRepository.findAllNationalCode();
        for (String nationalCode : allSavedNationalCodes) {
            if (nationalCodeEntered.equals(nationalCode)) {
                invalidNationalCode = true;
                logger.info("nationalCode " + nationalCodeEntered + " is used before.");
            }
        }
        return invalidNationalCode;
    }
}
