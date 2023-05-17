package ir.co.isc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.isc.entity.Cards;
import ir.co.isc.entity.Customers;
import ir.co.isc.service.cards.CardService;
import ir.co.isc.service.customers.CustomersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Card")
@RequiredArgsConstructor
@Tag(name = "Card", description = "مدیریت کارت")
public class CardController {

    private final CardService cardService;

    private final CustomersService customersService;

    /*  @PostMapping("/registerCard")
    @Operation(summary = "ثبت کارت")
    private ResponseEntity<Cards> registerCard(@RequestBody Cards cards) {

        return entityService.createNewCard(cards);

    }*/
  @PostMapping("/registerCard")
  @Operation(summary = "ثبت کارت")
 // private ResponseEntity<Customers> registerCard(@RequestBody Cards cards) {
  private ResponseEntity<Customers> registerCard(@RequestParam(required = false) Customers customers, @RequestParam(required = false) Cards cards) {

      return customersService.addCard(customers,cards);

  }
   @GetMapping
    @Operation(summary = "جستجوی کارت با کد ملی یا شماره حساب")
    private ResponseEntity<Cards> getCard(@RequestParam(required = false) String nationalCode, @RequestParam(required = false) String accountNumber) {

        return cardService.findCardsByNationalCodeOrAccountNumber(nationalCode, accountNumber);

    }
   /* @Operation(summary = "جستجوی کارت با شماره کارت")
    private ResponseEntity<Cards>findCardsByCardNumber( @RequestParam(required = false) String cardNumber) {
        return entityService.findCardsByCardNumber(cardNumber);
    }*/

}
