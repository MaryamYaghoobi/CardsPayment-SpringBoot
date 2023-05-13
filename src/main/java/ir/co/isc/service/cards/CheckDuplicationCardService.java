package ir.co.isc.service.cards;

import ir.co.isc.service.customers.CustomersService;
import org.apache.logging.log4j.core.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CheckDuplicationCardService {

   // Logger logger = Logger.getRootLogger();
    private static final Logger logger = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public boolean checkDuplicateNationalCode(String nationalCodeEntered) {
        boolean invalidNationalCode = false;

        List<String> allSavedNationalCodes = CustomersService.getInstance().findAllNationalCode();
        for (String nationalCode : allSavedNationalCodes) {
            if (nationalCodeEntered.equals(nationalCode)) {
                invalidNationalCode = true;
                logger.info("nationalCode " + nationalCodeEntered + " is used before.");
            }
        }
        return invalidNationalCode;
    }

    public boolean checkDuplicateCards(String customersNationalCode, String cardTypeEntered) {

        boolean duplicateBinCard = false;
        boolean duplicateTypeCard = false;
        List<String> binCardsNumber = CardsService.getInstance().findBinCards(customersNationalCode);
        for (String customersBinCard : binCardsNumber)
            if (customersNationalCode.equals(customersBinCard)) {
                logger.info("This issuer existed" + duplicateBinCard);
                duplicateBinCard = true;
            }
        if (duplicateBinCard) {
            List<String> cardType = CardsService.getInstance().findTypeCards(String.valueOf(binCardsNumber));
            for (String cards : cardType)
                if (cardTypeEntered.equals(cards))
                    duplicateTypeCard = true;
            logger.info("This type card exists for" + duplicateTypeCard);
            return duplicateTypeCard;
        }
        return duplicateBinCard;
    }
}



