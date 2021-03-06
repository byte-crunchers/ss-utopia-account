package com.ssutopia.financial.accountService.controller;


import java.net.URI;
import java.util.*;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.ssutopia.financial.accountService.dto.PaymentDto;
import com.ssutopia.financial.accountService.dto.CardStatusDto;
import com.ssutopia.financial.accountService.dto.ReportForm;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CardForm;
import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.service.CardServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


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
	public static final String MAPPING = EndpointConstants.API_V_0_1_CARDS;
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


	@PostMapping(value = "/{cardId}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cards> requestNewCard(@PathVariable Long cardId){
		var card = cardService.requestNewCard(cardId);
		Long newCardId = card.getCardNum();
		var uri = URI.create(MAPPING+"/"+newCardId);
		return ResponseEntity.created(uri).body(card);

	}



	// report card as stolen
	@PostMapping(value = "/report", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> reportCardAsStolen(@RequestBody ReportForm reportForm) {
		System.out.println("Card reported as stolen with account ID = " + reportForm.getAccountId());

		Long accountId = Long.parseLong(reportForm.getAccountId());
		Accounts a = cardService.disableCard(accountId);

		if(a != null)
			return ResponseEntity.noContent().build();  //status code 204
		else
			return ResponseEntity.unprocessableEntity().build();  //status code 422
	}


	@GetMapping(value = "/creditlimit/{id}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Integer viewCreditLimit(@PathVariable Long id){
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
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(card.getAccounts().getId())
				.toUri();

		boolean sendEmail = false;
		if(sendEmail) {
	        //sending confirm request to email server
	        String url = EndpointConstants.API_V_0_1_CARDSEMAILCONFIRM;
	
	        // for testing email server
			// cause email use h2, it only has a few dummy data
			int min = 1;
			int max = 13;
			int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
	
			RestTemplate restTemplate = new RestTemplate();
	
			Map<String, Object> map = new HashMap<>();
			map.put("email", card.getAccounts().getUsers().getEmail());
			map.put("firstName", card.getAccounts().getUsers().getFirst_name());
			map.put("account_id", random_int);
	
			try
			{ResponseEntity<Void> response = restTemplate.postForEntity(url, map, Void.class);
			}catch (Exception ex){
				System.out.println(ex.toString());
			}
		}
		
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
