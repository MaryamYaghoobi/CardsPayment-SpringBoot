package ir.co.isc.service.cards;

import ir.co.isc.entity.Cards;
import ir.co.isc.service.customers.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenerationCardNumberService {
      Cards card = new Cards();
    private final CardService cardService;


    public String generateCardNumber(Cards issuerCode) {
          String binCards  = cardService.getSelectedIssuerName(cardsBin[0], cardsBin[1]);
          StringBuilder CardNumber = new StringBuilder(String.valueOf(issuerCode));
          CardNumber.append(binCards);
          int checkDigit = this.getCheckDigit(CardNumber.toString());
          CardNumber.append(checkDigit);

          return CardNumber.toString();



      //-------------------------
      Cards selectedBinCard = CardsService.getInstance().getSelectedIssuerName(cardsBin[0], cardsBin[1]);
    StringBuilder CardNumber = new StringBuilder(String.valueOf(selectedBinCard));
            CardNumber.append(selectedBinCard);
            CardNumber.append(checkDigit);
            card.setCardNumber(String.valueOf(CardNumber));}
    //------------------------------

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
}

