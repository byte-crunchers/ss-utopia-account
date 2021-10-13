package com.ssutopia.financial.accountService.controller;

import com.ssutopia.financial.accountService.dto.CreditLimitDto;
import com.ssutopia.financial.accountService.entity.CardForm;
import com.ssutopia.financial.accountService.dto.CardStatusDto;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.service.CardService;
import com.ssutopia.financial.accountService.service.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.API_V_0_1_CARDS)
public class CardController {
    private final CardServiceImpl cardService;
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

    
    // view card status by user id
    @GetMapping(value = "/credit/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CardStatusDto>> getAllCardsByUserId(@PathVariable Long id) {
        List<CardStatusDto> cards = cardService.getAllCardsByUserId(id);
        if (cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cards);
    }

    // get all debit cards by user id
    @GetMapping(value = "/debit/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<CardStatusDto>> getDebitCardsByUserId(@PathVariable Long id) {
        List<CardStatusDto> cards = cardService.getDebitCardsByUserId(id);
        if (cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cards);
    }


	@GetMapping(value = "/creditlimit/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public int viewCreditLimit(@PathVariable Long id){
    	return cardService.viewCreditLimit(id);
	}

	@PutMapping(value = "/increasecreditlimit",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> increaseCreditLimit(@Valid @RequestBody CreditLimitDto creditLimitDto){
    	cardService.increaseCreditLimit(creditLimitDto);
		return ResponseEntity.noContent().build();
	}


	// receive card application form & print to console
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> applyForCard(@RequestBody CardForm newCardForm) {
		System.out.println("Received new card application form:");
		newCardForm.printFields();

		delay();

		Cards card = cardService.createNewCard(newCardForm);
		System.out.println("Created a new card instance:");
		card.printFields();

		// set location header
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(card.getCardNum())
				.toUri();

		// return status code 201
		return ResponseEntity.created(location).build();
	}

	// get a single card instance
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<Cards> getCard(@PathVariable Long id) {
		return cardService.getCard(id);
	}

	// pretend to think for a few seconds while processing the form
	private void delay() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
