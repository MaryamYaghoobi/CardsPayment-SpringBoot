package ir.co.isc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ir.co.isc.entity.Cards;
import ir.co.isc.service.cards.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Card")
@RequiredArgsConstructor
@Tag(name = "Card", description = "مدیریت کارت")
public class CardController {

    private final CardService entityService;

    @PostMapping("/register")
    @Operation(summary = "ثبت کارت")
    private ResponseEntity<Cards> registerCard(@RequestBody Cards cards) {

        return entityService.create(cards);

    }
   @GetMapping
    @Operation(summary = "جستجوی کارت با کد ملی یا شماره حساب")
    private ResponseEntity<Cards> getCard(@RequestParam(required = false) String nationalCode, @RequestParam(required = false) String accountNumber) {

        return entityService.findCardsByNationalCodeOrAccountNumber(nationalCode, accountNumber);

    }
    @Operation(summary = "جستجوی کارت با شماره کارت")
    private ResponseEntity<Cards>findCardsByCardNumber( @RequestParam(required = false) String cardNumber) {

        return entityService.findCardsByCardNumber(cardNumber);
    }

}
