package com.ssutopia.financial.accountService.controller;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.API_V_0_1_CARDS)
public class CardController {
    private final CardService cardService;
    @GetMapping(value = "/credit",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CreditAccount>> getCreditCards() {
        List<CreditAccount> cards = cardService.getCreditCards();
        if (cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cards);
    }

    @GetMapping(value = "/debit",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<DebitAccount>> getDebitCards() {
        List<DebitAccount> cards = cardService.getDebitCards();
        if (cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cards);
    }
}
